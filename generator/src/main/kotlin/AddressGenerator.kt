package algo.terminal.algovanity.generator

import algo.terminal.algovanity.generator.utils.repeatUntilCancelled
import algo.terminal.algovanity.utils.createLogger
import com.algorand.algosdk.account.Account
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.launch
import model.AlgoAddress
import kotlin.coroutines.CoroutineContext

class AddressGenerator(
	private var desiredNumberOfJobs: Int = Runtime.getRuntime().availableProcessors(),
	autoStart: Boolean = false,
) : CoroutineScope {
	private val logger = createLogger()
	private val supervisorJob = SupervisorJob()
	private val addressGenerators = mutableListOf<Job>()
	private var currentlyAdjusting = false
	val output = Channel<AlgoAddress>()

	override val coroutineContext: CoroutineContext
		get() = Dispatchers.IO + supervisorJob

	init {
		if (autoStart) start()
	}

	fun start() = launch {
		adjustAddressGenerators()
	}

	private fun CoroutineScope.startGenerator(number: Int) = launch {
		repeatUntilCancelled("Vanity address generator #$number") {
			val address = Account().let {
				AlgoAddress(address = it.address.toString(), secret = it.toMnemonic())
			}
			output.send(address)
		}
	}

	private fun adjustAddressGenerators(): GeneratorAdjustmentOutcome {
		if (currentlyAdjusting) {
			logger.warn("Generator adjustment already in progress, request is denied")
			return GeneratorAdjustmentOutcome.IN_PROGRESS
		}
		currentlyAdjusting = true

		logger.info("Adjusting number of address generators from ${addressGenerators.size} to $desiredNumberOfJobs")
		while (addressGenerators.size < desiredNumberOfJobs) {
			logger.debug("Adding message processor...")
			val number = addressGenerators.size + 1
			addressGenerators.add(startGenerator(number))
		}

		while (addressGenerators.size > desiredNumberOfJobs) {
			logger.debug("Removing message processor...")
			addressGenerators.removeLastOrNull()?.cancel()
		}
		logger.info("Done adjusting number of address generators")

		currentlyAdjusting = false
		return GeneratorAdjustmentOutcome.SUCCESS
	}
}
