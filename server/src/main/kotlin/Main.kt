package algo.terminal.algovanity.server

import algo.terminal.algovanity.server.plugins.configureHttp
import algo.terminal.algovanity.server.plugins.configureRouting
import algo.terminal.algovanity.server.plugins.configureSerialization
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty

fun main() {
	// Initialize the object
	AlgoVanityServer

	embeddedServer(Netty, port = 8080, host = "0.0.0.0") {
		configureRouting()
		configureHttp()
		configureSerialization()
	}.start(wait = true)
}
