package algo.terminal.algovanity.server.controller.admin

import algo.terminal.algovanity.server.model.wrapper.ResponseWrapper
import algo.terminal.algovanity.server.model.wrapper.setResponse
import algo.terminal.algovanity.server.service.generator.GeneratorService
import algo.terminal.algovanity.utils.createLogger
import io.ktor.http.HttpStatusCode
import io.ktor.server.resources.get
import io.ktor.server.routing.Routing
import org.koin.ktor.ext.inject

fun Routing.installAdminGeneratorRoutes() {
	val logger = createLogger()
	logger.info("Installing admin generator routes")
	val addressGeneratorService by inject<GeneratorService>()

	get<Admin.Generator.Status> {
		logger.debug("Received request for generator status")
		setResponse(
			ResponseWrapper(data = "Not implemented yet", status = HttpStatusCode.NotImplemented)
		)
	}

	get<Admin.Generator.Start> {
		logger.debug("Received request to start the generator")
		addressGeneratorService.start()
		setResponse(ResponseWrapper(data = "Started the address generator service"))
	}

	get<Admin.Generator.Stop> {
		logger.debug("Received request to stop the generator")
		addressGeneratorService.stop()
		setResponse(ResponseWrapper(data = "Stopped the address generator service"))
	}
}
