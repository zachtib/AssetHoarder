package com.zachtib.assets.lib.state

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
        values[key] = wrapped
    }

    @PublishedApi
    internal fun copyValues(): Map<String, Value> {
        return values.toMap()
    }

    inline operator fun <reified T : Any> get(key: String): T? {
        return when (val wrapped = getWrappedValue(key)) {
            is IntValue -> wrapped.value as? T
            is LongValue -> wrapped.value as? T
            is StringValue -> wrapped.value as? T
            null -> return null
        }
    }

    inline operator fun <reified T : Any> set(key: String, value: T) {
        val wrapped = when (value) {
            is Int -> IntValue(value)
            is Long -> LongValue(value)
            is String -> StringValue(value)
            else -> return
        }
        setWrappedValue(key, wrapped)
    }
}
