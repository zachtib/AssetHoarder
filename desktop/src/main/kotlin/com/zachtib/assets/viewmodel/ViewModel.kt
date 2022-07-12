package com.zachtib.assets.viewmodel

import com.zachtib.assets.lib.Closeable
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import kotlin.coroutines.CoroutineContext

abstract class ViewModel() : CoroutineScope, Closeable {

    override val coroutineContext: CoroutineContext = SupervisorJob() + Dispatchers.Main.immediate

    private val closeables = mutableSetOf<Closeable>()

    protected fun addCloseable(closeable: Closeable) {
        closeables += closeable
    }

    override fun close() {
        for (item in closeables) {
            item.close()
        }
        this.cancel()
    }
}
