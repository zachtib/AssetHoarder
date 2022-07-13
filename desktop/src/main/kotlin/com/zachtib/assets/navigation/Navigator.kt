package com.zachtib.assets.navigation

import com.zachtib.lib.backstack.BackstackScope

fun interface Navigator<K> {
    suspend fun perform(
        action: BackstackScope<K>.() -> Unit,
    )
}
