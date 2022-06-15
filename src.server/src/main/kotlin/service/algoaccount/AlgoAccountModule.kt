package algo.terminal.algovanity.server.service.algoaccount

import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val algoAccountModule = module {
	singleOf(::AlgoAccountServiceImpl) { bind<AlgoAccountService>() }
}
