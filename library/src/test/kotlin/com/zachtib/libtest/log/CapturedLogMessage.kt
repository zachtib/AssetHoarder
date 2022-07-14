package com.zachtib.libtest.log

import com.zachtib.lib.log.Level

data class CapturedLogMessage(
    val level: Level,
    val tag: String,
    val message: String,
    val throwable: Throwable?,
)
