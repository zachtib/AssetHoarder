package com.zachtib.lib.log

inline val <T : Any> T.tag: String
    get() = this.javaClass.simpleName

@Suppress("NOTHING_TO_INLINE")
inline fun <T : Any> T.log(
    level: Level = Level.DEBUG,
    message: () -> String,
) {
    Logger.GLOBAL.log(level, tag, message())
}
