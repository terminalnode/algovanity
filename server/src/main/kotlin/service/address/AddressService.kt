package algo.terminal.algovanity.server.service.address

import model.AlgoAddress

interface AddressService {
	/** Persist some addresses to the database. */
	suspend fun persist(algoAddresses: Collection<AlgoAddress>)

	/** Get all addresses stored in the database. */
	suspend fun getAll(): Collection<AlgoAddress>

	/** Get all addresses stored in the database and starting with the provided [prefix]. */
	suspend fun getAllStartingWith(prefix: String): Collection<AlgoAddress>

	/**
	 * Delete an address from the database.
	 * This method of deletion is preferred and requires that the matching secret is provided as well.
	 */
	suspend fun delete(algoAddress: AlgoAddress)

	/**
	 * Delete an address from the database without providing its secret key.
	 * This method of deletion should be avoided as the actor has not proven ownership of the address.
	 */
	suspend fun unsafeDelete(algoAddress: String)
}
