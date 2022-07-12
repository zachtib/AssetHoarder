package com.zachtib.assets.navigation

import com.zachtib.assets.lib.Closeable
import com.zachtib.assets.lib.state.StateHandle
import kotlinx.serialization.Serializable

@Serializable
class BackstackEntry(
    val key: ScreenKey,
    val state: StateHandle,
) : Closeable {

    override fun close() {
        // Nothing yet.
    }
}