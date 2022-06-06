package algo.terminal.algovanity.server.model.wrapper

import io.ktor.http.HttpStatusCode
import io.ktor.server.application.ApplicationCall
import io.ktor.server.application.call
import io.ktor.server.response.respond
import io.ktor.util.pipeline.PipelineContext
import kotlinx.serialization.Contextual

// Would've liked to use generic type <T> here, but kotlinx.serialization is not a fan.
suspend fun PipelineContext<Unit, ApplicationCall>.setResponse(message: ResponseWrapper<Any>) {
	with(call) {
		response.status(message.status)
		respond(message)
	}
}

data class ResponseWrapper<T>(
	@Contextual val data: T,
	@Contextual val status: HttpStatusCode,
)
