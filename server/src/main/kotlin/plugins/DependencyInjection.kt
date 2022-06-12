package algo.terminal.algovanity.server.plugins

import algo.terminal.algovanity.server.service.generator.generatorModule
import io.ktor.server.application.Application
import io.ktor.server.application.install
import org.koin.ktor.plugin.Koin
import org.koin.logger.slf4jLogger

fun Application.configureDependencyInjection() {
	install(Koin) {
		slf4jLogger()
		modules(
			generatorModule,
		)
	}
}
