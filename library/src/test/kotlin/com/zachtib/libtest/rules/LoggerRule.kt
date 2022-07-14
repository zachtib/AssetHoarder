package com.zachtib.libtest.rules

import com.zachtib.lib.log.Logger
import com.zachtib.libtest.log.CapturedLogMessage
import com.zachtib.libtest.log.TestLogger
import com.zachtib.libtest.statement
import org.junit.rules.TestRule
import org.junit.runner.Description
import org.junit.runners.model.Statement

class LoggerRule : TestRule {

    private lateinit var _logger: TestLogger

    val logger: TestLogger
        get() = _logger

    val messages: List<CapturedLogMessage>
        get() = _logger.messages

    override fun apply(base: Statement, description: Description) = statement {
        val previousLogger = Logger.GLOBAL

        _logger = TestLogger()
        Logger.GLOBAL = _logger

        try {
            base.evaluate()
        } finally {
            Logger.GLOBAL = previousLogger
        }
    }
}
