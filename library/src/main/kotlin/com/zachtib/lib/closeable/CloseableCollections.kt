package com.zachtib.lib.closeable

fun <K, V : Closeable> Map<K, V>.closeAll() {
    for (closeable in values) {
        closeable.close()
    }
}

interface CloseableMap<K, V : Closeable> : Map<K, V>, Closeable {
    override fun close() {
        closeAll()
    }
}

interface MutableCloseableMap<K, V : Closeable> : MutableMap<K, V>, CloseableMap<K, V>

private class DelegatingMutableCloseableMap<K, V : Closeable>(
    private val delegate: MutableMap<K, V>,
) : MutableCloseableMap<K, V>, MutableMap<K, V> by delegate

fun <K, V : Closeable> mutableCloseableMapOf(): MutableCloseableMap<K, V> {
    return DelegatingMutableCloseableMap(mutableMapOf())
}

fun <K, V : Closeable> mutableCloseableMapOf(vararg pairs: Pair<K, V>): MutableCloseableMap<K, V> {
    return DelegatingMutableCloseableMap(mutableMapOf(*pairs))
}
