package com.zachtib.lib.log

interface Logger {

    fun log(level: Level, tag: String, message: String, throwable: Throwable?)

    fun log(level: Level, tag: String, message: String) {
        log(level, tag, message, null)
    }

    companion object {
        var GLOBAL: Logger = EmptyLogger
    }
}

object EmptyLogger : Logger {
    override fun log(level: Level, tag: String, message: String, throwable: Throwable?) {
    }
}

class ConsoleLogger : Logger {
    override fun log(level: Level, tag: String, message: String, throwable: Throwable?) {
        TODO("Not yet implemented")
    }
}
