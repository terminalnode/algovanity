package algo.terminal.algovanity.server.controller.admin

import algo.terminal.algovanity.server.service.generator.AccountGeneratorService
import algo.terminal.algovanity.server.utils.AccountGeneratorStatusResponse
import algo.terminal.algovanity.server.utils.StringResponse
import algo.terminal.algovanity.server.utils.setResponse
import algo.terminal.algovanity.utils.createLogger
import io.ktor.server.resources.get
import io.ktor.server.routing.Routing
import org.koin.ktor.ext.inject

fun Routing.installAdminGeneratorRoutes() {
	val logger = createLogger()
	logger.info("Installing admin generator routes")
	val accountGeneratorService by inject<AccountGeneratorService>()

	get<Admin.Generator.Status> {
		logger.debug("Received request for generator status")
		setResponse(AccountGeneratorStatusResponse(data = accountGeneratorService.status()))
	}

	get<Admin.Generator.Start> {
		logger.debug("Received request to start the generator")
		accountGeneratorService.start()
		setResponse(StringResponse(data = "Started the account generator service"))
	}

	get<Admin.Generator.Stop> {
		logger.debug("Received request to stop the generator")
		accountGeneratorService.stop()
		setResponse(StringResponse(data = "Stopped the account generator service"))
	}
}
