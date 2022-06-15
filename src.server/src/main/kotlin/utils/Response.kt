package algo.terminal.algovanity.server.utils

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
