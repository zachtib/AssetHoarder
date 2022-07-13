package com.zachtib.lib.backstack

import kotlinx.serialization.KSerializer
import kotlinx.serialization.builtins.ListSerializer
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

class BackstackSerializer<K>(
    keySerializer: KSerializer<K>
) : KSerializer<Backstack<K>> {

    private val delegate = ListSerializer(BackstackEntry.serializer(keySerializer))

    override val descriptor: SerialDescriptor
        get() = delegate.descriptor

    override fun deserialize(decoder: Decoder): Backstack<K> {
        return Backstack(decoder.decodeSerializableValue(delegate))
    }

    override fun serialize(encoder: Encoder, value: Backstack<K>) {
        encoder.encodeSerializableValue(delegate, value.contents)
    }
}
