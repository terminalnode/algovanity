package algo.terminal.algovanity.server.config

import algo.terminal.algovanity.server.service.address.addressModule
import algo.terminal.algovanity.server.service.db.dbConnectionModule
import algo.terminal.algovanity.server.service.generator.generatorModule
import io.ktor.server.application.Application
import io.ktor.server.application.install
import org.koin.environmentProperties
import org.koin.fileProperties
import org.koin.ktor.plugin.Koin
import org.koin.logger.slf4jLogger

fun Application.configureDependencyInjection() {
	install(Koin) {
		slf4jLogger()

		fileProperties(fileName = "/algovanity.properties")
		environmentProperties()

		modules(
			addressModule,
			dbConnectionModule,
			generatorModule,
		)
	}
}
