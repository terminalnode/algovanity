package algo.terminal.algovanity.server.service.db

import algo.terminal.algovanity.utils.createLogger
import com.zaxxer.hikari.HikariDataSource
import kotlinx.coroutines.Dispatchers
import org.flywaydb.core.Flyway
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.transactions.experimental.newSuspendedTransaction

class DbConnectionImpl(
	private val hikariDataSource: HikariDataSource,
	dbCreationPolicy: DbCreationPolicy,
) : DbConnection {
	private val logger = createLogger()

	init {
		logger.info("Initializing database connection")

		// Migrations
		runMigrations(false)

		// Connect exposed
		Database.connect(hikariDataSource)
	}

	override suspend fun <T> query(block: suspend () -> T): T =
		newSuspendedTransaction(Dispatchers.IO) { block() }

	private fun configureFlyway(withBaseLineMigration: Boolean) =
		Flyway.configure()
			.dataSource(hikariDataSource)
			.baselineOnMigrate(withBaseLineMigration)
			.load()

	private fun runMigrations(clean: Boolean) {
		// TODO baseline should be true only on first run or, possibly, in non-live environments
		val flyway = configureFlyway(withBaseLineMigration = !clean)
		if (clean) flyway.clean()
		flyway.migrate()
	}
}
