package com.zachtib.lib.backstack

import com.zachtib.lib.closeable.Closeable
import com.zachtib.lib.closeable.closeAll
import com.zachtib.lib.closeable.mutableCloseableMapOf
import com.zachtib.lib.statehandle.StateHandle
import kotlinx.serialization.Serializable
import kotlinx.serialization.Transient

@Serializable
class BackstackEntry<K>(
    val key: K,
    val state: StateHandle,
) : Closeable {

    @Transient
    private val closeables = mutableCloseableMapOf<String, Closeable>()

    fun getCloseable(key: String): Closeable? {
        return closeables[key]
    }

    fun putCloseable(key: String, closeable: Closeable): Closeable? {
        return closeables.put(key, closeable)?.also { previous ->
            previous.close()
        }
    }

    override fun close() {
        closeables.closeAll()
    }
}
