package algo.terminal.algovanity.server.service.algoaccount

import model.AlgoAccount

interface AlgoAccountService {
	/** Persist some addresses to the database. */
	suspend fun persist(algoAccounts: Collection<AlgoAccount>)

	/** Get all addresses stored in the database. */
	suspend fun getAll(): Collection<AlgoAccount>

	/** Get all addresses stored in the database and starting with the provided [prefix]. */
	suspend fun getAllStartingWith(prefix: String): Collection<AlgoAccount>

	/**
	 * Delete an address from the database.
	 * This method of deletion is preferred and requires that the matching secret is provided as well.
	 */
	suspend fun delete(algoAccount: AlgoAccount)

	/**
	 * Delete an address from the database without providing its secret key.
	 * This method of deletion should be avoided as the actor has not proven ownership of the address.
	 */
	suspend fun unsafeDelete(algoAddress: String)
}
