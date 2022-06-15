@file:Suppress("unused") // parents are always unused

package algo.terminal.algovanity.server.controller.admin

import io.ktor.resources.Resource
import kotlinx.serialization.Serializable

@Serializable
@Resource("/admin")
class Admin {
	@Serializable
	@Resource("/generator")
	class Generator(val parent: Admin) {
		@Serializable
		@Resource("/status")
		class Status(val parent: Generator)

		@Serializable
		@Resource("/start")
		class Start(val parent: Generator)

		@Serializable
		@Resource("/stop")
		class Stop(val parent: Generator)
	}
}
