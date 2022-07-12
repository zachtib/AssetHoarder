package com.zachtib.assets.lib.state

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
sealed class Value

@Serializable
@SerialName("int")
data class IntValue(val value: Int) : Value()

@Serializable
@SerialName("long")
data class LongValue(val value: Long) : Value()

@Serializable
@SerialName("string")
data class StringValue(val value: String) : Value()

inline fun <reified T : Any> Value.unwrap(): T? {
    return when (this) {
        is IntValue -> value as? T
        is LongValue -> value as? T
        is StringValue -> value as? T
    }
}

inline fun <reified T : Any> wrappedValueOf(value: T): Value? {
    return when (value) {
        is Int -> IntValue(value)
        is Long -> LongValue(value)
        is String -> StringValue(value)
        else -> null
    }
}
