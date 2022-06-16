package algo.terminal.algovanity.server.utils.ext

import org.koin.core.scope.Scope

fun Scope.getBooleanProperty(key: String, defaultValue: Boolean) =
	when (val stringProp = getProperty(key, defaultValue.toString())) {
		"true", "1", "t" -> true
		"false", "0", "f" -> false
		else -> throw IllegalStateException("Failed to parse boolean property $key=$stringProp")
	}
