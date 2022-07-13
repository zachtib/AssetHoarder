package com.zachtib.lib.statehandle

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

inline fun <reified T : Any> StateHandle.getOrSet(key: String, defaultValue: () -> T): T {
    return when (val wrapped = getWrappedValue(key)) {
        is IntValue -> wrapped.value as T
        is LongValue -> wrapped.value as T
        is StringValue -> wrapped.value as T
        null -> {
            val value = defaultValue()
            set(key, value)
            value
        }
    }
}

inline fun <reified T : Any> StateHandle.state(key: String, default: T): MutableState<T> {
    val wrapped = mutableStateOf(getOrSet(key) { default })
    return object : MutableState<T> by wrapped {
        override var value: T
            get() = wrapped.value
            set(value) {
                set(key, value)
                wrapped.value = value
            }
    }
}

inline fun <reified T : Any> StateHandle.default(crossinline default: () -> T) = object : ReadWriteProperty<Any, T> {
    override operator fun setValue(thisRef: Any, property: KProperty<*>, value: T) {
        set(property.name, value)
    }

    override operator fun getValue(thisRef: Any, property: KProperty<*>): T {
        return getOrSet(property.name, default)
    }
}
