package com.zachtib.lib.log

import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisallowComposableCalls

@PublishedApi
internal inline val <T : Any> T.classNameTag: String
    get() = this::class.simpleName ?: "Missing Tag"

@Suppress("NOTHING_TO_INLINE")
inline fun <T : Any> T.log(
    level: Level = Level.DEBUG,
    tag: String? = null,
    message: () -> String,
) {
    if (Logger.GLOBAL.shouldLog(level)) {
        Logger.GLOBAL.log(level, tag ?: classNameTag, message())
    }
}

private val ignoredClassPackages = arrayOf("com.zachtib.lib.log", "androidx.compose")
private val ignoredMethodNames = arrayOf("invoke")

private inline val composeTag: String
    get() {
        return RuntimeException()
            .stackTrace
            .drop(1)
            .firstOrNull { element ->
                !ignoredClassPackages.any { element.className.startsWith(it) }
                    && element.methodName !in ignoredMethodNames
            }?.methodName ?: "UnknownComposeMethod"
    }

@Composable
fun log(
    level: Level = Level.VERBOSE,
    tag: String? = null,
    buildMessage: @DisallowComposableCalls () -> String,
) {
    if (Logger.GLOBAL.shouldLog(level)) {
        Logger.GLOBAL.log(level, tag ?: composeTag, buildMessage())
    }
}