/** This is used only when running the generator standalone. */
package algo.terminal.algovanity.generator

import algo.terminal.algovanity.utils.createLogger
import kotlinx.coroutines.runBlocking

val logger = createLogger()

suspend fun main() {
	logger.info("Running AlgoVanity Generator standalone, all addresses will be logged and discarded")
	val generator = AccountGenerator()
	generator.start()

	runBlocking {
		for (address in generator.output) {
			logger.info(address.toString())
		}
	}
}
