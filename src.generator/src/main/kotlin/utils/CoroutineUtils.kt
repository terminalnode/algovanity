package algo.terminal.algovanity.generator.utils

import algo.terminal.algovanity.utils.createLogger
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.isActive
import kotlinx.coroutines.yield
import kotlin.coroutines.cancellation.CancellationException

private val logger = createLogger()

suspend fun CoroutineScope.repeatUntilCancelled(
	label: String = "unnamed coroutine",
	block: suspend () -> Unit,
) {
	while (isActive) {
		try {
			logger.debug("running coroutine: $label")
			block()
			yield()
		} catch (e: CancellationException) {
			logger.error("coroutine '$label' cancelled: $e")
		} catch (e: Exception) {
			logger.error("coroutine '$label' exception: $e")
			e.printStackTrace()
		}
	}

	logger.info("coroutine '$label' exiting")
}
