package algo.terminal.algovanity.server.service.generator

import algo.terminal.algovanity.generator.AccountGenerator
import algo.terminal.algovanity.server.service.algoaccount.AlgoAccountService
import algo.terminal.algovanity.utils.createLogger
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import kotlinx.datetime.Clock
import kotlinx.datetime.LocalDateTime
import model.AlgoAccount
import kotlin.coroutines.CoroutineContext

class AccountBatchingWorker(
	private val accountGenerator: AccountGenerator,
	private val algoAccountService: AlgoAccountService,
	private val batchSize: Int,
) : CoroutineScope {
	private val logger = createLogger()
	private var batchesProcessed = 0
	private var batchIndex = 0
	private var batch = arrayOfNulls<AlgoAccount>(batchSize)
	private val startTime = Clock.System.now()

	private val supervisorJob = SupervisorJob()
	override val coroutineContext: CoroutineContext
		get() = Dispatchers.IO + supervisorJob

	fun start() = launch {
		while (isActive) {
			for (account in accountGenerator.output) {
				addAccount(account)
			}
		}
		persist()
	}

	fun stop() {
		supervisorJob.cancel()
	}

	fun getBatchesProcessed() = batchesProcessed
	fun getCurrentBatchIndex() = batchIndex
	fun getRunningTime() = Clock.System.now() - startTime

	private suspend fun addAccount(account: AlgoAccount) {
		batch[batchIndex] = account
		batchIndex++

		if (batchIndex == batchSize) {
			persist()
			resetBatch()
		}
	}

	private fun resetBatch() {
		batchIndex = 0
		batchesProcessed++
		batch = arrayOfNulls(batchSize)
		logger.info("$batchesProcessed batches (size $batchSize) processed")
	}

	private suspend fun persist() {
		algoAccountService.persist(batch.filterNotNull())
	}
}
