package algo.terminal.algovanity.server.controller.accounts

import algo.terminal.algovanity.server.service.algoaccount.AlgoAccountService
import algo.terminal.algovanity.server.utils.AlgoAddressResponse
import algo.terminal.algovanity.server.utils.StringResponse
import algo.terminal.algovanity.server.utils.setResponse
import algo.terminal.algovanity.utils.createLogger
import io.ktor.server.application.call
import io.ktor.server.request.receive
import io.ktor.server.resources.get
import io.ktor.server.resources.post
import io.ktor.server.routing.Routing
import org.koin.ktor.ext.inject

fun Routing.installAccountsController() {
	val logger = createLogger()
	logger.info("Installing accounts controller")
	val algoAccountService by inject<AlgoAccountService>()

	get<Accounts> {
		logger.debug("Received request to list all accounts")
		setResponse(AlgoAddressResponse(data = algoAccountService.getAll()))
	}

	get<Accounts.StartsWith> { query ->
		logger.debug("Received request to query account addresses starting with '${query.query}'")
		setResponse(AlgoAddressResponse(data = algoAccountService.getAllStartingWith(query.query)))
	}

	post<Accounts.Create> {
		logger.debug("Received request to create accounts")
		val newAddress = call.receive<CreateAccountsRequest>().accounts
		algoAccountService.persist(newAddress)
		setResponse(StringResponse(data = "Addresses have been saved to the database."))
	}
}
