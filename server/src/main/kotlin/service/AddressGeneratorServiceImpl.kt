package algo.terminal.algovanity.server.service

import algo.terminal.algovanity.generator.AddressGenerator
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val addressGeneratorModule = module {
	single { AddressGenerator() }
	singleOf(::AddressGeneratorServiceImpl) { bind<AddressGeneratorService>() }
}

interface AddressGeneratorService {
	/** Start the address generator. */
	fun start()

	/** Stop the address generator. */
	fun stop()
}

class AddressGeneratorServiceImpl(
	private val addressGenerator: AddressGenerator,
) : AddressGeneratorService {
	override fun start() {
		addressGenerator.start()
	}

	override fun stop() {
		TODO("Stop functionality not implemented yet")
	}
}
