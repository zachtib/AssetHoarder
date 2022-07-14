package com.zachtib.lib.log

import org.koin.core.logger.MESSAGE
import org.koin.core.logger.Level as KoinLevel
import org.koin.core.logger.Logger as KoinLogger

internal class KoinLoggerAdapter(
    private val delegate: Logger,
) : KoinLogger(delegate.level.toKoinLevel()) {

    override fun log(level: KoinLevel, msg: MESSAGE) {
        delegate.log(level = level.toLevel(), tag = KOIN_TAG, message = msg)
    }

    companion object {
        private const val KOIN_TAG = "Koin"

        private fun KoinLevel.toLevel() = when (this) {
            KoinLevel.DEBUG -> Level.DEBUG
            KoinLevel.INFO -> Level.INFO
            KoinLevel.ERROR -> Level.ERROR
            KoinLevel.NONE -> Level.ASSERT
        }

        private fun Level.toKoinLevel() = when (this) {
            Level.VERBOSE, Level.DEBUG -> KoinLevel.DEBUG
            Level.INFO -> KoinLevel.INFO
            Level.WARNING -> KoinLevel.INFO
            Level.ERROR -> KoinLevel.ERROR
            Level.ASSERT -> KoinLevel.NONE
        }
    }
}

fun Logger.toKoinLogger(): KoinLogger = KoinLoggerAdapter(this)
