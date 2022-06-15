package algo.terminal.algovanity.server.service.generator

import kotlinx.serialization.Serializable

interface AccountGeneratorService {
	/** Start generating accounts. */
	fun start()

	/** Stop generating accounts. */
	fun stop()

	/** Get a status report. */
	fun status(): AccountGeneratorStatus
}

@Serializable
data class AccountGeneratorStatus(
	val status: String,
	val isRunning: Boolean,
)
