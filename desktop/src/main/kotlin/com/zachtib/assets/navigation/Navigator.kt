package com.zachtib.assets.navigation

fun interface Navigator {
    suspend fun perform(
        action: BackstackScope.() -> Unit,
    )
}
