package algo.terminal.algovanity.server.controller.address

import algo.terminal.algovanity.server.model.wrapper.ResponseWrapper
import algo.terminal.algovanity.server.model.wrapper.setResponse
import algo.terminal.algovanity.utils.createLogger
import io.ktor.http.HttpStatusCode
import io.ktor.server.resources.get
import io.ktor.server.routing.Routing
import io.ktor.server.routing.post

val logger = createLogger()

fun Routing.installAddressController() {
	get<Addresses> { query ->
		// TODO Get all addresses
		logger.debug("Received request to list all addresses")
		setResponse(
			ResponseWrapper(data = query, status = HttpStatusCode.NotImplemented)
		)
	}

	get<Addresses.StartsWith> { query ->
		// TODO Get addresses starting with the provided string
		logger.debug("Received request to query addresses starting with '${query.query}'")
		setResponse(
			ResponseWrapper(data = query, status = HttpStatusCode.NotImplemented)
		)
	}

	post<Addresses.Create> { request ->
		// TODO Create a new address
		logger.debug("Received request to create address ${request.request}")
		setResponse(
			ResponseWrapper(data = request, status = HttpStatusCode.NotImplemented)
		)
	}
}
