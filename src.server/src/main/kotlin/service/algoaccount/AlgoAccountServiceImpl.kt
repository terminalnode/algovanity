package algo.terminal.algovanity.server.service.algoaccount

import algo.terminal.algovanity.persistence.account.AccountModel
import algo.terminal.algovanity.persistence.account.AccountTable
import algo.terminal.algovanity.server.service.db.DbConnection
import model.AlgoAccount
import org.jetbrains.exposed.sql.and
import org.jetbrains.exposed.sql.batchInsert
import org.jetbrains.exposed.sql.deleteWhere
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.selectAll

class AlgoAccountServiceImpl(
	private val dbConnection: DbConnection,
) : AlgoAccountService {
	override suspend fun persist(
		algoAccounts: Collection<AlgoAccount>,
	): Unit = dbConnection.query {
		AccountTable.batchInsert(algoAccounts, ignore = true) { algoAddress ->
			this[AccountTable.address] = algoAddress.address
			this[AccountTable.secret] = algoAddress.secret
		}
	}

	override suspend fun getAll(): Collection<AlgoAccount> = dbConnection.query {
		AccountTable.selectAll().map { AccountModel.wrapRow(it).toAlgoAddress() }
	}

	override suspend fun getAllStartingWith(
		prefix: String,
	): Collection<AlgoAccount> = dbConnection.query {
		AccountTable
			.select { AccountTable.address like "$prefix%" }
			.map { AccountModel.wrapRow(it).toAlgoAddress() }
	}

	override suspend fun delete(
		algoAccount: AlgoAccount,
	): Unit = dbConnection.query {
		AccountTable.deleteWhere {
			AccountTable.address eq algoAccount.address and (AccountTable.secret eq algoAccount.secret)
		}
	}

	override suspend fun unsafeDelete(
		algoAddress: String,
	): Unit = dbConnection.query {
		AccountTable.deleteWhere {
			AccountTable.address eq algoAddress
		}
	}
}
