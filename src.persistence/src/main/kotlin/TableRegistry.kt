package algo.terminal.algovanity.persistence

import algo.terminal.algovanity.persistence.account.AccountTable
import algo.terminal.algovanity.utils.createLogger
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.transactions.transaction

object TableRegistry {
	private val logger = createLogger()
	private val tables = arrayOf<Table>(
		AccountTable,
	)

	/** Create all tables in the specified database, or default database if none specified. */
	fun createTables(database: Database? = null) {
		logger.info("Creating tables in ${database?.url ?: "default database"}")
		transaction(database) {
			SchemaUtils.create(*tables)
		}
	}

	/** Drop  and recreate all tables in the specified database, or default database if none specified. */
	fun dropCreateTables(database: Database? = null) {
		logger.info("Dropping/creating tables in ${database?.url ?: "default database"}")
		transaction(database) {
			SchemaUtils.drop(*tables)
			SchemaUtils.create(*tables)
		}
	}
}
