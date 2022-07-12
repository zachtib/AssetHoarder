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
