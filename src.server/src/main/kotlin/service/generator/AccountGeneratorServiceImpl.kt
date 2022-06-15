package algo.terminal.algovanity.server.service.generator

import algo.terminal.algovanity.generator.AccountGenerator
import algo.terminal.algovanity.server.service.algoaccount.AlgoAccountService
import algo.terminal.algovanity.utils.createLogger

class AccountGeneratorServiceImpl(
	private val accountGenerator: AccountGenerator,
	private val algoAccountService: AlgoAccountService,
	private val batchSize: Int,
	autoStart: Boolean,
) : AccountGeneratorService {
	private val logger = createLogger()
	private var worker: AccountBatchingWorker? = null

	init {
		if (autoStart) start()
	}

	override fun start() {
		if (worker == null) {
			logger.info("Launching AccountBatchingWorker")
			worker = AccountBatchingWorker(accountGenerator, algoAccountService, batchSize).also { it.start() }
		} else {
			logger.warn("Received request to start AccountBatchingWorker, but it's already running")
		}
	}

	override fun stop() {
		worker?.stop()
			?.also { logger.info("Stopping AccountBatchingWorker") }
			?: logger.warn("Received request to stop AccountBatchingWorker, but it's not running")
		worker = null
	}

	override fun status(): AccountGeneratorStatus {
		val worker = worker ?: return AccountGeneratorStatus("Inactive", false)
		val batched = batchSize * worker.getBatchesProcessed().toLong()
		val current = worker.getCurrentBatchIndex()
		val duration = worker.getRunningTime()
		return AccountGeneratorStatus(
			status = "$batched processed ($current pending, running for $duration)",
			isRunning = true,
		)
	}
}
