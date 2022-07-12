package com.zachtib.assets.navigation

import com.zachtib.assets.lib.Closeable
import com.zachtib.assets.lib.closeAll
import com.zachtib.assets.lib.state.StateHandle
import kotlinx.serialization.Serializable

@Serializable
class BackstackEntry(
    val key: ScreenKey,
    val state: StateHandle,
) : Closeable {

    @PublishedApi
    internal val closables = mutableMapOf<String, Closeable>()

    override fun close() {
        closables.closeAll()
    }
}
