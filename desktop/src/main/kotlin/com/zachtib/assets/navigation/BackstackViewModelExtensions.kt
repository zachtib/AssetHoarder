package com.zachtib.assets.navigation

import com.zachtib.assets.lib.Closeable
import com.zachtib.assets.lib.state.StateHandle
import com.zachtib.assets.viewmodel.ViewModel

@PublishedApi
internal const val VIEWMODEL_KEY = "com.zachtib.assets.navigation.ViewModel"

@PublishedApi
internal inline fun <reified K : ScreenKey, reified VM : ViewModel> BackstackEntry.createViewModel(
    viewModelKey: String,
    initializer: (K, StateHandle) -> VM
): VM {
    require(key is K) {
        "That's an error, was expecting ${K::class.simpleName} but found ${key::class.simpleName}"
    }
    val createdViewModel: VM = initializer(key, state)
    closables[viewModelKey] = createdViewModel
    return createdViewModel
}

inline fun <reified K : ScreenKey, reified VM : ViewModel> BackstackEntry.viewModel(
    key: String? = null,
    initializer: (K, StateHandle) -> VM
): VM {
    val viewModelKey = key ?: VIEWMODEL_KEY

    return when (val cachedViewModel: Closeable? = closables[viewModelKey]) {
        is VM -> cachedViewModel
        null -> createViewModel(viewModelKey, initializer)
        else -> {
            // TODO: Log a warning here
            cachedViewModel.close()
            createViewModel(viewModelKey, initializer)
        }
    }
}
