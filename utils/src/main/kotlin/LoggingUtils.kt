package algo.terminal.algovanity.utils

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import java.lang.invoke.MethodHandles

/** Create a logger for the class in which the method is called. */
@JvmName("createLoggerStatic")
@Suppress("NOTHING_TO_INLINE")
inline fun createLogger(): Logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass())
