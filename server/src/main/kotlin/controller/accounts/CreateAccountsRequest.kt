package algo.terminal.algovanity.server.controller.accounts

import kotlinx.serialization.Serializable
import model.AlgoAccount

@Serializable
data class CreateAccountsRequest(
	val accounts: List<AlgoAccount>,
)
