package com.zachtib.lib.log

import com.zachtib.lib.log.Level.DEBUG
import com.zachtib.lib.log.Level.VERBOSE
import com.zachtib.lib.log.Level.WARNING
import com.zachtib.libtest.log.assertLogged
import com.zachtib.libtest.log.assertLoggedWhere
import com.zachtib.libtest.log.assertNoErrors
import com.zachtib.libtest.log.assertNoWarnings
import com.zachtib.libtest.rules.LoggerRule
import org.junit.Rule
import kotlin.test.*

class LoggerTests {
    @get:Rule val loggerRule = LoggerRule()

    @Test
    fun `test that calling log uses the TestLogger`() {
        log { "Hello, Log" }
        val count = loggerRule.logger.messages.size

        assertEquals(1, count)
    }

    @Test
    fun `test making a TestLogger assertion`() {
        log { "My helpful message" }

        with(loggerRule) {
            assertLogged(DEBUG, "LoggerTests", "My helpful message")
        }
    }

    @Test
    fun `test a TestLogger assertion with a condition`() {
        log(VERBOSE) { "This is a message" }

        with(loggerRule) {
            assertLoggedWhere { message == "This is a message" }
        }
    }

    @Test
    fun `test a TestLogger assertion with a condition and count`() {
        log(VERBOSE) { "This is a message" }
        log(VERBOSE) { "This is some other message" }
        log(VERBOSE) { "This is yet another message" }

        with(loggerRule) {
            assertLoggedWhere(exactly = 1) {
                message == "This is some other message"
            }
        }
    }

    @Test
    fun `test asserting no warnings`() {
        log(VERBOSE) { "This is a message" }
        log(DEBUG) { "This is some other message" }
        log(VERBOSE) { "Just logging some messages, here" }

        with(loggerRule) {
            assertNoWarnings()
        }
    }

    @Test
    fun `test asserting no errors`() {
        log(WARNING) { "Something might be wrong..." }
        log(DEBUG) { "This is some other message" }
        log(VERBOSE) { "Just logging some messages, here" }

        loggerRule.assertNoErrors()
    }
}
