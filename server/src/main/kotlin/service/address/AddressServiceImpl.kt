package algo.terminal.algovanity.server.service.address

import algo.terminal.algovanity.persistence.account.AccountModel
import algo.terminal.algovanity.persistence.account.AccountTable
import algo.terminal.algovanity.server.service.db.DbConnection
import model.AlgoAddress
import org.jetbrains.exposed.sql.and
import org.jetbrains.exposed.sql.batchInsert
import org.jetbrains.exposed.sql.deleteWhere
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.selectAll

class AddressServiceImpl(
	private val dbConnection: DbConnection,
) : AddressService {
	override suspend fun persist(algoAddresses: Collection<AlgoAddress>): Unit = dbConnection.query {
		AccountTable.batchInsert(algoAddresses) { algoAddress ->
			this[AccountTable.address] = algoAddress.address
			this[AccountTable.secret] = algoAddress.secret
		}
	}

	override suspend fun getAll(): Collection<AlgoAddress> = dbConnection.query {
		AccountTable.selectAll().map { AccountModel.wrapRow(it).toAlgoAddress() }
	}

	override suspend fun getAllStartingWith(prefix: String): Collection<AlgoAddress> = dbConnection.query {
		AccountTable
			.select { AccountTable.address like "$prefix%" }
			.map { AccountModel.wrapRow(it).toAlgoAddress() }
	}

	override suspend fun delete(algoAddress: AlgoAddress): Unit = dbConnection.query {
		AccountTable.deleteWhere {
			AccountTable.address eq algoAddress.address and (AccountTable.secret eq algoAddress.secret)
		}
	}

	override suspend fun unsafeDelete(algoAddress: String): Unit = dbConnection.query {
		AccountTable.deleteWhere {
			AccountTable.address eq algoAddress
		}
	}
}
