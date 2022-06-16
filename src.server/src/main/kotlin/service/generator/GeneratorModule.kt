package algo.terminal.algovanity.server.service.generator

import algo.terminal.algovanity.generator.AccountGenerator
import algo.terminal.algovanity.server.utils.ext.getBooleanProperty
import org.koin.dsl.bind
import org.koin.dsl.module

val accountGeneratorModule = module {
	// Starting the account generator is safe as the output channel will fill up and
	// in effect keep it halted until a consumer of the output is applied.
	single { AccountGenerator().also { it.start() } }

	single(createdAtStart = true) {
		AccountGeneratorServiceImpl(
			accountGenerator = get(),
			algoAccountService = get(),
			batchSize = getProperty("generator.batch-size", 100_000),
			autoStart = getBooleanProperty("generator.auto-start", false),
		)
	}.bind<AccountGeneratorService>()
}
