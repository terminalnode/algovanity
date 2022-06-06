package algo.terminal.algovanity.server.controller.address

import io.ktor.resources.Resource
import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable
import model.AlgoAddress

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
		@Contextual val request: AlgoAddress,
	)
}
