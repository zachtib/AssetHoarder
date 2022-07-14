package com.zachtib.lib.backstack

fun interface Navigator<K> {
    suspend fun perform(
        action: BackstackScope<K>.() -> Unit,
    )
}

fun <K> Backstack<K>.createNavigator() = Navigator<K> { action -> action() }
