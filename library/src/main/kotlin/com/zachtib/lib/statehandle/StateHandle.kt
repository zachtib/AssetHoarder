package com.zachtib.lib.statehandle

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.mapNotNull
import kotlinx.serialization.Serializable

@Serializable(with = StateHandleSerializer::class)
class StateHandle(
    initialState: Map<String, Value> = emptyMap(),
) {
    private val values: MutableMap<String, Value> = initialState.toMutableMap()

    @PublishedApi
    internal fun getWrappedValue(key: String): Value? {
        return values[key]
    }

    @PublishedApi
    internal fun setWrappedValue(key: String, wrapped: Value) {
        sharedFlows[key]?.tryEmit(wrapped)
        values[key] = wrapped
    }

    @PublishedApi
    internal fun copyValues(): Map<String, Value> {
        return values.toMap()
    }

    inline operator fun <reified T : Any> get(key: String): T? {
        val wrapped = getWrappedValue(key) ?: return null
        return wrapped.unwrap()
    }

    inline operator fun <reified T : Any> set(key: String, value: T) {
        val wrapped = wrappedValueOf(value) ?: return
        setWrappedValue(key, wrapped)
    }

    private val sharedFlows = mutableMapOf<String, MutableSharedFlow<Value>>()

    @PublishedApi
    internal fun getOrCreateSharedFlow(key: String): SharedFlow<Value> {
        return sharedFlows.getOrPut(key) {
            val createdFlow = MutableSharedFlow<Value>(replay = 1)
            val currentValue = getWrappedValue(key)
            if (currentValue != null) {
                createdFlow.tryEmit(currentValue)
            }
            createdFlow
        }
    }

    inline fun <reified T : Any> getFlow(key: String): Flow<T> {
        return getOrCreateSharedFlow(key).mapNotNull { wrapped ->
            wrapped.unwrap()
        }
    }
}
