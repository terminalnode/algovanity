package algo.terminal.algovanity.server.model

import kotlinx.serialization.Serializable

@Serializable
data class AddressDto(
	val address: String,
	val secret: String,
)
