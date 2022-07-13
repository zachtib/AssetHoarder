package com.zachtib.assets.navigation

import com.zachtib.assets.lib.Closeable
import com.zachtib.assets.lib.closeAll
import com.zachtib.assets.lib.mutableCloseableMapOf
import com.zachtib.assets.lib.state.StateHandle
import kotlinx.serialization.Serializable
import kotlinx.serialization.Transient

@Serializable
class BackstackEntry(
    val key: ScreenKey,
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
