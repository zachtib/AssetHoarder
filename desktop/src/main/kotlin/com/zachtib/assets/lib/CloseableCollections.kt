package com.zachtib.assets.lib

fun <K, V : Closeable> Map<K, V>.closeAll() {
    for (closeable in values) {
        closeable.close()
    }
}
