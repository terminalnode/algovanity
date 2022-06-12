package algo.terminal.algovanity.server.service.generator

interface GeneratorService {
	/** Start consuming addresses output by the address generator. */
	fun start()

	/** Stop consuming addresses output by the address generator. */
	fun stop()
}
