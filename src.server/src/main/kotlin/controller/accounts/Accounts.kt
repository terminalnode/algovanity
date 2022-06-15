@file:Suppress("unused") // parents are always unused

package algo.terminal.algovanity.server.controller.accounts

import io.ktor.resources.Resource
import kotlinx.serialization.Serializable

@Serializable
@Resource("/addresses")
class Accounts {
	@Serializable
	@Resource("/starts-with/{query}")
	class StartsWith(val parent: Accounts, val query: String)

	@Serializable
	@Resource("/create")
	class Create(val parent: Accounts)
}
