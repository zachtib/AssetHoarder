package com.zachtib.assets.uuid

import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

object UUIDStringSerializer : KSerializer<UUID> {
    override val descriptor = PrimitiveSerialDescriptor("UUID", PrimitiveKind.STRING)

    override fun serialize(encoder: Encoder, value: UUID) {
        encoder.encodeString(value.uuidString)
    }

    override fun deserialize(decoder: Decoder): UUID {
        val uuidString = decoder.decodeString()
        return UUID(uuidString) ?: throw IllegalArgumentException("Asked to decode invalid uuidString: $uuidString")
    }
}
