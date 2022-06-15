package algo.terminal.algovanity.server.utils

import algo.terminal.algovanity.server.service.generator.AccountGeneratorStatus
import io.ktor.http.HttpStatusCode
import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable
import model.AlgoAccount

interface Response<T> {
	val data: T
	val status: HttpStatusCode
}

@Serializable
class StringResponse(
	override val data: String,
	@Contextual override val status: HttpStatusCode = HttpStatusCode.OK,
) : Response<String>

@Serializable
class AlgoAddressResponse(
	override val data: Collection<AlgoAccount>,
	@Contextual override val status: HttpStatusCode = HttpStatusCode.OK,
) : Response<Collection<AlgoAccount>>

@Serializable
class AccountGeneratorStatusResponse(
	override val data: AccountGeneratorStatus,
	@Contextual override val status: HttpStatusCode = HttpStatusCode.OK,
) : Response<AccountGeneratorStatus>
