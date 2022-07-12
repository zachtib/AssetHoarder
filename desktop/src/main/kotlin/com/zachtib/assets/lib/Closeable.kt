package com.zachtib.assets.lib

import java.io.Closeable as JavaCloseable

interface Closeable : JavaCloseable {
    override fun close()
}
