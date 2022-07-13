package com.zachtib.lib.closeable

import java.io.Closeable as JavaCloseable

interface Closeable : JavaCloseable {
    override fun close()
}
