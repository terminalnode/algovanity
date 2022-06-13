package algo.terminal.algovanity.server.controller.address

import algo.terminal.algovanity.server.service.address.AddressService
import algo.terminal.algovanity.server.utils.AlgoAddressResponse
import algo.terminal.algovanity.server.utils.setResponse
import algo.terminal.algovanity.utils.createLogger
import io.ktor.server.resources.get
import io.ktor.server.resources.post
import io.ktor.server.routing.Routing
import org.koin.ktor.ext.inject

fun Routing.installAddressController() {
	val logger = createLogger()
	logger.info("Installing address controller")
	val addressService by inject<AddressService>()

	get<Addresses> {
		logger.debug("Received request to list all addresses")
		setResponse(AlgoAddressResponse(data = addressService.getAll()))
	}

	get<Addresses.StartsWith> { query ->
		logger.debug("Received request to query addresses starting with '${query.query}'")
		setResponse(AlgoAddressResponse(data = addressService.getAllStartingWith(query.query)))
	}

	post<Addresses.Create> { request ->
		logger.debug("Received request to create address $request")
		val newAddress = request.body
		addressService.persist(newAddress)
		setResponse(AlgoAddressResponse(data = listOf(newAddress)))
	}
}
