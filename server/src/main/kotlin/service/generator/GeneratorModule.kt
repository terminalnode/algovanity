package algo.terminal.algovanity.server.service.generator

import algo.terminal.algovanity.generator.AddressGenerator
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val generatorModule = module {
	// Starting the address generator is safe as the output channel will fill up and
	// in effect keep it halted until a consumer of the output is applied.
	single { AddressGenerator().also { it.start() } }
	singleOf(::GeneratorServiceImpl) { bind<GeneratorService>() }
}
