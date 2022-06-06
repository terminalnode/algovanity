package algo.terminal.algovanity.server

import algo.terminal.algovanity.generator.AddressGenerator

object AlgoVanityServer {
	init {
	}

	val addressGenerator = AddressGenerator(autoStart = true)
}
