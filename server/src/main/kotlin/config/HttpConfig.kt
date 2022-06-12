package algo.terminal.algovanity.server.config

import algo.terminal.algovanity.utils.createLogger
import io.ktor.http.HttpHeaders
import io.ktor.http.HttpMethod
import io.ktor.server.application.Application
import io.ktor.server.application.install
import io.ktor.server.plugins.cors.routing.CORS

private val logger = createLogger()

fun Application.configureHttp(
	host: String? = null,
	schemes: List<String> = listOf("http"),
) {
	install(CORS) {
		allowMethod(HttpMethod.Options)
		allowMethod(HttpMethod.Put)
		allowMethod(HttpMethod.Delete)
		allowMethod(HttpMethod.Patch)
		allowHeader(HttpHeaders.Authorization)

		if (host != null) {
			allowHost(host = host, schemes = schemes)
		} else {
			logger.warn("No host configured, allowing from any host")
			anyHost() // @TODO: Don't do this in production if possible. Try to limit it.
		}
	}
}
