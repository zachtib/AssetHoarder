package com.zachtib.assets.lib.state

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import kotlinx.serialization.Serializable

@Serializable(with = StateHandleSerializer::class)
class StateHandle(
    initialState: Map<String, StateValue> = emptyMap(),
) {

    private val stateMap: MutableMap<String, StateValue> = initialState.toMutableMap()

    val map: Map<String, StateValue>
        get() = stateMap.toMap()

    fun putInt(key: String, value: Int) {
        stateMap[key] = StateValue.IntValue(value)
    }

    fun getInt(key: String): Int? {
        return when (val stateValue = stateMap[key]) {
            is StateValue.IntValue -> stateValue.value
            else -> null
        }
    }

    fun putString(key: String, value: String) {
        stateMap[key] = StateValue.StringValue(value)
    }

    fun getString(key: String): String? {
        return when (val stateValue = stateMap[key]) {
            is StateValue.StringValue -> stateValue.value
            else -> null
        }
    }

    fun string(key: String, initializer: () -> String): MutableState<String> {
        val current: StateValue.StringValue = when (val stateValue = stateMap[key]) {
            is StateValue.StringValue -> stateValue
            else -> {
                val newStateValue = StateValue.StringValue(initializer())
                stateMap[key] = newStateValue
                newStateValue
            }
        }

        val wrapped: MutableState<String> = mutableStateOf(current.value)
        return MutableStateHandle(wrapped) { value ->
            stateMap[key] = StateValue.StringValue(value)
        }
    }

    fun int(key: String, initializer: () -> Int): MutableState<Int> {
        val current: StateValue.IntValue = when (val stateValue = stateMap[key]) {
            is StateValue.IntValue -> stateValue
            else -> {
                val newStateValue = StateValue.IntValue(initializer())
                stateMap[key] = newStateValue
                newStateValue
            }
        }

        val wrapped: MutableState<Int> = mutableStateOf(current.value)
        return MutableStateHandle(wrapped) { value ->
            stateMap[key] = StateValue.IntValue(value)
        }
    }
}
