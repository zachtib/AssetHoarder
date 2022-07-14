package com.zachtib.lib.viewmodels

import com.zachtib.lib.closeable.Closeable
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import kotlin.coroutines.CoroutineContext

abstract class ViewModel : CoroutineScope, Closeable {

    override val coroutineContext: CoroutineContext = SupervisorJob() + Dispatchers.Main.immediate

    override fun close() {
        this.cancel()
    }
}
