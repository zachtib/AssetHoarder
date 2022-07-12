package com.zachtib.assets.navigation

import com.zachtib.assets.lib.state.StateHandle
import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.builtins.ListSerializer
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

interface BackstackScope {
    fun push(key: ScreenKey)
    fun pop()
}

@Serializable(with = BackstackSerializer::class)
class Backstack internal constructor(
    initialBackstack: List<BackstackEntry>,
) : BackstackScope {

    companion object {
        private fun createBackstackEntry(key: ScreenKey): BackstackEntry {
            return BackstackEntry(
                key = key,
                state = StateHandle()
            )
        }
    }

    constructor(home: ScreenKey) : this(listOf(createBackstackEntry(home)))

    private val backstackEntries = initialBackstack.toMutableList()

    internal val contents: List<BackstackEntry> get() = backstackEntries.toList()

    internal val top: BackstackEntry get() = backstackEntries.last()

    override fun push(key: ScreenKey) {
        backstackEntries.add(createBackstackEntry(key))
    }

    override fun pop() {
        if (backstackEntries.size > 1) {
            val removed = backstackEntries.removeLast()
            removed.close()
        }
    }
}

object BackstackSerializer : KSerializer<Backstack> {

    private val delegate = ListSerializer(BackstackEntry.serializer())

    override val descriptor: SerialDescriptor
        get() = delegate.descriptor

    override fun deserialize(decoder: Decoder): Backstack {
        return Backstack(decoder.decodeSerializableValue(delegate))
    }

    override fun serialize(encoder: Encoder, value: Backstack) {
        encoder.encodeSerializableValue(delegate, value.contents)
    }
}
