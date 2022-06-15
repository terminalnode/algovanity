package algo.terminal.algovanity.server.controller.address

import kotlinx.serialization.Serializable
import model.AlgoAddress

@Serializable
data class CreateAccountsRequest(
	val accounts: List<AlgoAddress>,
)
