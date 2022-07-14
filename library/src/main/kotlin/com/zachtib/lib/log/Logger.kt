package com.zachtib.lib.log

import kotlinx.datetime.Clock

interface Logger {

    val level: Level get() = Level.INFO

    fun shouldLog(level: Level): Boolean {
        return level >= this.level
    }

    fun log(level: Level, tag: String, message: String, throwable: Throwable?)

    fun log(level: Level, tag: String, message: String) {
        log(level, tag, message, null)
    }

    companion object {
        var GLOBAL: Logger = EmptyLogger
            internal set
    }
}

object EmptyLogger : Logger {

    override fun shouldLog(level: Level) = false

    override fun log(level: Level, tag: String, message: String, throwable: Throwable?) {
        // Nothing
    }
}

class ConsoleLogger(
    override val level: Level = Level.INFO,
    private val truncateTagLength: Int = 15,
    private val clock: Clock = Clock.System,
) : Logger {

    private val levelPadValue = Level.values().maxOf { it.name.length }

    override fun log(level: Level, tag: String, message: String, throwable: Throwable?) {
        val timestamp = clock.now()
        val levelString = level.name.padEnd(levelPadValue)

        val tagString = when {
            tag.length > truncateTagLength -> tag.take(truncateTagLength)
            tag.length < truncateTagLength -> tag.padEnd(truncateTagLength)
            else -> tag
        }

        println("$timestamp $levelString $tagString $message")
        throwable?.printStackTrace()
    }
}

fun Logger.Companion.logToConsole(minimumLogLevel: Level = Level.INFO) {
    GLOBAL = ConsoleLogger(minimumLogLevel)
}
