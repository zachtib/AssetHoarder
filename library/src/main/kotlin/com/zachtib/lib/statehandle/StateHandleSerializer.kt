package com.zachtib.lib.statehandle

import kotlinx.serialization.KSerializer
import kotlinx.serialization.builtins.MapSerializer
import kotlinx.serialization.builtins.serializer
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

object StateHandleSerializer : KSerializer<StateHandle> {

    private val delegate = MapSerializer(String.serializer(), Value.serializer())

    override val descriptor: SerialDescriptor
        get() = delegate.descriptor

    override fun deserialize(decoder: Decoder): StateHandle {
        return StateHandle(decoder.decodeSerializableValue(delegate))
    }

    override fun serialize(encoder: Encoder, value: StateHandle) {
        encoder.encodeSerializableValue(delegate, value.copyValues())
    }
}
