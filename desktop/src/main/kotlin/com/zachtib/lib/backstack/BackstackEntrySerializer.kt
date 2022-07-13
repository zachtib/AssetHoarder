package com.zachtib.lib.backstack

//
// class BackstackEntrySerializer<T : BackstackKey>(
//     private val dataSerializer: KSerializer<T>
// ) : KSerializer<BackstackEntry<T>> {
//     override val descriptor = dataSerializer.descriptor
//     override fun serialize(encoder: Encoder, value: BackstackEntry<T>) = dataSerializer.serialize(encoder, value.contents)
//     override fun deserialize(decoder: Decoder) = Box(dataSerializer.deserialize(decoder))
//
//     fun foo() {
//         val serializer: KSerializer<BackstackEntry<T>> = BackstackEntry.serializer(dataSerializer)
//     }
// }
