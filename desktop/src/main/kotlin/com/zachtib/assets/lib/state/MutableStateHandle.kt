package com.zachtib.assets.lib.state

import androidx.compose.runtime.MutableState

internal class MutableStateHandle<T : Any>(
    private val wrapped: MutableState<T>,
    private val onValueChange: (T) -> Unit,
) : MutableState<T> by wrapped {
    override var value: T
        get() = wrapped.value
        set(value) {
            onValueChange(value)
            wrapped.value = value
        }
}