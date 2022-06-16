package algo.terminal.algovanity.server.service.db

import algo.terminal.algovanity.server.utils.ext.getBooleanProperty
import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import org.koin.core.scope.Scope
import org.koin.dsl.module
import org.postgresql.ds.PGSimpleDataSource

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
 * db.data-source:           org.postgresql.ds.PGSimpleDataSource
 * db.name:                  algodb
 * db.user:                  algouser
 * db.password:              algopassword
 * db.pool-size:             3
 * db.auto-commit:           false
 * db.transaction-isolation: TRANSACTION_REPEATABLE_READ
 * db.rewrite-batched:       true
 */
fun Scope.createHikariDataSource(): HikariDataSource {
	val dataSource = getPropertyOrNull("db.data-source")
		?: org.postgresql.ds.PGSimpleDataSource::class.qualifiedName
		?: throw IllegalStateException("Failed to get PostgreSQL data source class")

	return HikariConfig().apply {
		// driverClassName = driver
		dataSourceClassName = dataSource
		addDataSourceProperty(
			"databaseName",
			getProperty(key = "db.name", defaultValue = "algodb"),
		)
		addDataSourceProperty(
			"reWriteBatchedInserts",
			getBooleanProperty("db.rewrite-batched", true),
		)
		username = getProperty(key = "db.user", defaultValue = "algouser")
		password = getProperty(key = "db.password", defaultValue = "algopassword")
		maximumPoolSize = getProperty(key = "db.pool-size", defaultValue = 3)
		isAutoCommit = getProperty(key = "db.auto-commit", defaultValue = false)
		transactionIsolation = getProperty(key = "db.transaction-isolation", defaultValue = "TRANSACTION_REPEATABLE_READ")
		validate()
	}.let { HikariDataSource(it) }
}
