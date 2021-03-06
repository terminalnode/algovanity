package algo.terminal.algovanity.server.controller.admin

import algo.terminal.algovanity.utils.createLogger
import io.ktor.server.resources.get
import io.ktor.server.routing.Routing

fun Routing.installAdminController() {
	val logger = createLogger()
	logger.info("Installing admin controller")

	// Install admin sub-routes
	installAdminGeneratorRoutes()
}
