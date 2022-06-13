package algo.terminal.algovanity.server.utils

import io.ktor.server.application.ApplicationCall
import io.ktor.server.application.call
import io.ktor.server.response.respond
import io.ktor.util.pipeline.PipelineContext

// Would've liked to use generic type <T> here, but kotlinx.serialization is not a fan.
suspend inline fun <reified T> PipelineContext<Unit, ApplicationCall>.setResponse(
	responseData: Response<T>,
) {
	with(call) {
		respond(responseData)
		response.status(responseData.status)
	}
}
