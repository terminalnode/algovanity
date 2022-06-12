package algo.terminal.algovanity.server

import algo.terminal.algovanity.server.plugins.configureDependencyInjection
import algo.terminal.algovanity.server.plugins.configureHttp
import algo.terminal.algovanity.server.plugins.configureRouting
import algo.terminal.algovanity.server.plugins.configureSerialization
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
