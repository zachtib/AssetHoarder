package com.zachtib.assets.lib.state

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
sealed class StateValue {
    @Serializable
    @SerialName("int")
    data class IntValue(val value: Int) : StateValue()

    @Serializable
    @SerialName("string")
    data class StringValue(val value: String) : StateValue()
}
