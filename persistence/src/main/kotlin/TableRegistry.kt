package algo.terminal.algovanity.persistence

import algo.terminal.algovanity.persistence.account.AccountTable
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.transactions.transaction

object TableRegistry {
	private val tables = arrayOf<Table>(
		AccountTable,
	)

	/** Create all tables in the specified database, or default database if none specified. */
	fun createTables(database: Database? = null) {
		transaction(database) {
			SchemaUtils.create(*tables)
		}
	}

	/** Drop all tables in the specified database, or default database if none specified. */
	fun dropTables(database: Database? = null) {
		transaction(database) {
			SchemaUtils.drop(*tables)
		}
	}
}
