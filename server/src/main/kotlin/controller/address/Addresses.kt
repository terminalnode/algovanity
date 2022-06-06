package algo.terminal.algovanity.server.controller.address

import algo.terminal.algovanity.server.model.AddressDto
import io.ktor.resources.Resource
import kotlinx.serialization.Serializable

@Serializable
@Resource("/addresses")
class Addresses {
	@Serializable
	@Resource("/starts-with/{query}")
	class StartsWith(
		val parent: Addresses,
		val query: String,
	)

	@Serializable
	@Resource("/create")
	class Create(
		val parent: Addresses,
		val request: AddressDto,
	)
}
