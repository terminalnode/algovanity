package algo.terminal.algovanity.persistence.account

import org.jetbrains.exposed.dao.id.LongIdTable
import org.jetbrains.exposed.sql.Column

object AccountTable : LongIdTable() {
	val address: Column<String> = char("account", 58)
		.uniqueIndex("IDX_account_account")

	val secret: Column<String> = varchar("secret", 256)
}
