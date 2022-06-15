package algo.terminal.algovanity.server.service.db

interface DbConnection {
	suspend fun <T> query(block: suspend () -> T): T
}
