package com.zachtib.assets.lib.state

import com.zachtib.assets.viewmodel.ViewModel
import kotlinx.serialization.Serializable
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

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

    fun putInt(key: String, value: Int) {
        values[key] = IntValue(value)
    }

    fun getInt(key: String): Int? {
        return when (val stateValue = values[key]) {
            is IntValue -> stateValue.value
            else -> null
        }
    }

    fun putString(key: String, value: String) {
        values[key] = StringValue(value)
    }

    fun getString(key: String): String? {
        return when (val stateValue = values[key]) {
            is StringValue -> stateValue.value
            else -> null
        }
    }
}

class TestModel(state: StateHandle) : ViewModel() {
    var name: String by state.default { "" }
}

private inline fun <reified T : Any> StateHandle.default(crossinline default: () -> T) = object : ReadWriteProperty<Any, T> {
    override operator fun setValue(thisRef: Any, property: KProperty<*>, value: T) {
        set(property.name, value)
    }

    override operator fun getValue(thisRef: Any, property: KProperty<*>): T {
        val current: T? = get(property.name)
        if (current != null) {
            return current
        }
        val newValue: T = default()
        set(property.name, newValue)
        return newValue
    }
}
