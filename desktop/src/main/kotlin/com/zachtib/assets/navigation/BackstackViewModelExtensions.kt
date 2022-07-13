package com.zachtib.assets.navigation

import com.zachtib.assets.viewmodel.ViewModel
import kotlin.reflect.KClass

private const val DEFAULT_VIEWMODEL_KEY = "com.zachtib.assets.navigation.ViewModel"

@PublishedApi
internal fun <VM : ViewModel> BackstackEntry.viewModel(
    key: String?,
    vmClass: KClass<VM>,
    initializer: () -> VM,
): VM {
    val viewModelKey = key ?: DEFAULT_VIEWMODEL_KEY

    val cachedViewModel = closables[viewModelKey]
    if (cachedViewModel != null && vmClass.isInstance(cachedViewModel)) {
        @Suppress("UNCHECKED_CAST")
        return cachedViewModel as VM
    }

    val createdViewModel: VM = initializer()
    closables.put(viewModelKey, createdViewModel)?.let { existing ->
        // TODO: Make this a proper logger.warn
        println("Warning: Was expecting a viewModel of type ${vmClass.simpleName} at location $viewModelKey, but existing value was $existing")
        // At the very least, we'll close the existing item
        existing.close()
    }

    return createdViewModel
}

inline fun <reified VM : ViewModel> BackstackEntry.viewModel(
    key: String? = null,
    noinline initializer: () -> VM,
): VM = viewModel(key, VM::class, initializer)
