package com.zachtib.libtest.log

import com.zachtib.lib.log.Level
import com.zachtib.libtest.rules.LoggerRule
import kotlin.test.*

fun LoggerRule.assertLogged(
    level: Level,
    tag: String,
    message: String,
    throwable: Throwable? = null
) {
    assertTrue("$message was not found in logged items.") {
        messages.any {
            it.level == level &&
                it.tag == tag &&
                it.message == message &&
                it.throwable == throwable
        }
    }
}

fun LoggerRule.assertLoggedWhere(
    condition: CapturedLogMessage.() -> Boolean,
) {
    assertTrue("No messages matching the given condition were logged") {
        messages.any(condition)
    }
}

fun LoggerRule.assertLoggedWhere(
    exactly: Int = 1,
    condition: CapturedLogMessage.() -> Boolean,
) {
    assertEquals(exactly, messages.count(condition))
}

fun LoggerRule.assertNoErrors() {
    assertEquals(0, messages.count { it.level >= Level.ERROR })
}

fun LoggerRule.assertNoWarnings() {
    assertEquals(0, messages.count { it.level >= Level.WARNING })
}
