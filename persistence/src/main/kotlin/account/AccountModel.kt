package algo.terminal.algovanity.persistence.account

import model.AlgoAddress
import org.jetbrains.exposed.dao.LongEntity
import org.jetbrains.exposed.dao.LongEntityClass
import org.jetbrains.exposed.dao.id.EntityID

class AccountModel(id: EntityID<Long>) : LongEntity(id) {
	companion object : LongEntityClass<AccountModel>(AccountTable)
	var address by AccountTable.address
	var secret by AccountTable.secret

	fun toAlgoAddress() = AlgoAddress(address, secret)
}
