package algo.terminal.algovanity.server

import algo.terminal.algovanity.server.config.configureDependencyInjection
import algo.terminal.algovanity.server.config.configureHttp
import algo.terminal.algovanity.server.config.configureRouting
import algo.terminal.algovanity.server.config.configureSerialization
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty

fun main() {
	embeddedServer(Netty, port = 8080, host = "0.0.0.0") {
		configureDependencyInjection()
		configureRouting()
		configureHttp()
		configureSerialization()
	}.start(wait = true)
}
