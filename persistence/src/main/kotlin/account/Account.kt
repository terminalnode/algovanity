package algo.terminal.algovanity.persistence.account

import org.jetbrains.exposed.dao.LongEntity
import org.jetbrains.exposed.dao.LongEntityClass
import org.jetbrains.exposed.dao.id.EntityID

class Account(id: EntityID<Long>) : LongEntity(id) {
	companion object : LongEntityClass<Account>(AccountTable)
	var address by AccountTable.address
	var secret by AccountTable.secret
}
