package algo.terminal.algovanity.server.controller.admin

import io.ktor.resources.Resource
import kotlinx.serialization.Serializable

@Serializable
@Resource("/admin")
class Admin {
	@Serializable
	@Resource("/generator")
	class Generator {
		@Serializable
		@Resource("/start")
		class Start

		@Serializable
		@Resource("/start")
		class Stop
	}
}
