package algo.terminal.algovanity.server.service.db

import algo.terminal.algovanity.persistence.TableRegistry
import algo.terminal.algovanity.server.service.db.DbCreationPolicy.Create
import algo.terminal.algovanity.server.service.db.DbCreationPolicy.DropCreate
import algo.terminal.algovanity.server.service.db.DbCreationPolicy.None
import algo.terminal.algovanity.utils.createLogger
import com.zaxxer.hikari.HikariDataSource
import kotlinx.coroutines.Dispatchers
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.transactions.experimental.newSuspendedTransaction

enum class DbCreationPolicy {
	Create,
	DropCreate,
	None,
	;

	companion object {
		fun fromProperty(property: String) = when (property.lowercase()) {
			Create.toString().lowercase() -> Create
			DropCreate.toString().lowercase() -> DropCreate
			None.toString().lowercase() -> None
			else -> throw IllegalStateException("Invalid DbCreationPolicy: $property")
		}
	}
}

class DbConnectionImpl(
	hikariDataSource: HikariDataSource,
	dbCreationPolicy: DbCreationPolicy,
) : DbConnection {
	private val logger = createLogger()

	init {
		logger.info("Initializing database connection")
		val db = Database.connect(hikariDataSource)
		createTables(db, dbCreationPolicy)
	}

	private fun createTables(
		db: Database,
		dbCreationPolicy: DbCreationPolicy,
	): Unit = when (dbCreationPolicy) {
		Create -> TableRegistry.createTables(db)
		DropCreate -> { TableRegistry.dropCreateTables(db) }
		None -> { /* do nothing */ }
	}

	override suspend fun <T> query(block: suspend () -> T): T =
		newSuspendedTransaction(Dispatchers.IO) { block() }
}
