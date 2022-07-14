package com.zachtib.libtest.log

import com.zachtib.lib.log.Level
import com.zachtib.lib.log.Logger

class TestLogger : Logger {

    private val _messages = mutableListOf<CapturedLogMessage>()

    val messages: List<CapturedLogMessage>
        get() = _messages.toList()

    override fun log(level: Level, tag: String, message: String, throwable: Throwable?) {
        _messages.add(CapturedLogMessage(level, tag, message, throwable))
    }
}
