package algo.terminal.algovanity.server.service.db

import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import org.koin.core.scope.Scope
import org.koin.dsl.module

val dbConnectionModule = module {

	single { createHikariDataSource() }
	single<DbConnection>(createdAtStart = true) {
		val creationPolicy = getProperty("db.creation-policy", "None")
		DbConnectionImpl(
			hikariDataSource = get(),
			dbCreationPolicy = DbCreationPolicy.fromProperty(creationPolicy),
		)
	}
}

/**
 * Generate the Hikari Data Source
 *
 * Default values
 * db.driver:                org.postgresql.Driver
 * db.url:                   jdbc:postgresql://localhost:5432/algodb
 * db.user:                  algouser
 * db.password:              algopassword
 * db.pool-size:             3
 * db.auto-commit:           false
 * db.transaction-isolation: TRANSACTION_REPEATABLE_READ
 */
fun Scope.createHikariDataSource(): HikariDataSource {
	val driver = getPropertyOrNull("db.driver")
		?: org.postgresql.Driver::class.qualifiedName
		?: throw IllegalStateException("Failed to get PostgreSQL driver class")

	return HikariConfig().apply {
		driverClassName = driver
		jdbcUrl = getProperty(key = "db.url", defaultValue = "jdbc:postgresql://localhost:5432/algodb")
		username = getProperty(key = "db.user", defaultValue = "algouser")
		password = getProperty(key = "db.password", defaultValue = "algopassword")
		maximumPoolSize = getProperty(key = "db.pool-size", defaultValue = 3)
		isAutoCommit = getProperty(key = "db.auto-commit", defaultValue = false)
		transactionIsolation = getProperty(key = "db.transaction-isolation", defaultValue = "TRANSACTION_REPEATABLE_READ")
		validate()
	}.let { HikariDataSource(it) }
}
