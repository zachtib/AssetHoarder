package com.zachtib.lib

import com.zachtib.lib.backstack.BackstackEntry
import com.zachtib.lib.viewmodels.ViewModel
import kotlin.reflect.KClass

private const val DEFAULT_VIEWMODEL_KEY = "com.zachtib.assets.navigation.ViewModel"

@PublishedApi
internal fun <VM : ViewModel> BackstackEntry<*>.viewModel(
    key: String?,
    vmClass: KClass<VM>,
    initializer: () -> VM,
): VM {
    val viewModelKey = key ?: DEFAULT_VIEWMODEL_KEY

    val cachedViewModel = getCloseable(viewModelKey)
    if (cachedViewModel != null && vmClass.isInstance(cachedViewModel)) {
        @Suppress("UNCHECKED_CAST")
        return cachedViewModel as VM
    }

    val createdViewModel: VM = initializer()
    putCloseable(viewModelKey, createdViewModel)

    return createdViewModel
}

inline fun <reified VM : ViewModel> BackstackEntry<*>.viewModel(
    key: String? = null,
    noinline initializer: () -> VM,
): VM = viewModel(key, VM::class, initializer)
