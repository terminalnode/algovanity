package algo.terminal.algovanity.server.service.generator

import algo.terminal.algovanity.generator.AccountGenerator
import org.koin.dsl.bind
import org.koin.dsl.module

val accountGeneratorModule = module {
	// Starting the account generator is safe as the output channel will fill up and
	// in effect keep it halted until a consumer of the output is applied.
	single { AccountGenerator().also { it.start() } }

	single(createdAtStart = true) {
		val autoStart =
			when (val autoStartProp = getProperty("generator.auto-start", "false")) {
				"true" -> true
				"false" -> false
				else -> throw IllegalStateException("Failed to parse boolean property generator.auto-start=$autoStartProp")
			}

		AccountGeneratorServiceImpl(
			accountGenerator = get(),
			algoAccountService = get(),
			batchSize = getProperty("generator.batch-size", 100_000),
			autoStart = autoStart,
		)
	}.bind<AccountGeneratorService>()
}
