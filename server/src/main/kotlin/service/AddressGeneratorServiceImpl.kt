package algo.terminal.algovanity.server.service

import algo.terminal.algovanity.generator.AddressGenerator
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val addressGeneratorModule = module {
	// Starting the address generator is safe as the output channel will fill up and
	// in effect keep it halted until a consumer of the output is applied.
	single { AddressGenerator().also { it.start() } }
	singleOf(::AddressGeneratorServiceImpl) { bind<AddressGeneratorService>() }
}

interface AddressGeneratorService {
	/** Start consuming addresses output by the address generator. */
	fun start()

	/** Stop consuming addresses output by the address generator. */
	fun stop()
}

class AddressGeneratorServiceImpl(
	private val addressGenerator: AddressGenerator,
) : AddressGeneratorService {
	override fun start() {
		TODO("Start functionality not implemented yet")
	}

	override fun stop() {
		TODO("Stop functionality not implemented yet")
	}
}
