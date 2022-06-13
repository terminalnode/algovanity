package algo.terminal.algovanity.server.service.address

import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val addressModule = module {
	singleOf(::AddressServiceImpl) { bind<AddressService>() }
}
