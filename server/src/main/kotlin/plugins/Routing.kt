package algo.terminal.algovanity.server.plugins

import algo.terminal.algovanity.server.controller.address.installAddressController
import algo.terminal.algovanity.server.controller.admin.installAdminController
import io.ktor.server.application.Application
import io.ktor.server.application.install
import io.ktor.server.resources.Resources
import io.ktor.server.routing.routing

fun Application.configureRouting() {
	install(Resources)

	routing {
		installAdminController()
		installAddressController()
	}
}
