package com.zachtib.assets.navigation

import com.zachtib.assets.lib.state.StateHandle
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
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

    private val backstackEntries: MutableList<BackstackEntry>

    private val mutableCurrentEntryStateFlow: MutableStateFlow<BackstackEntry>

    val current: StateFlow<BackstackEntry>
        get() = mutableCurrentEntryStateFlow.asStateFlow()

    init {
        require(initialBackstack.isNotEmpty()) {
            "Can't initialize a Backstack with no ScreenKeys"
        }
        backstackEntries = initialBackstack.toMutableList()
        mutableCurrentEntryStateFlow = MutableStateFlow(backstackEntries.last())
    }

    companion object {
        private fun createBackstackEntry(key: ScreenKey): BackstackEntry {
            return BackstackEntry(
                key = key,
                state = StateHandle()
            )
        }
    }

    constructor(home: ScreenKey) : this(listOf(createBackstackEntry(home)))

    internal val contents: List<BackstackEntry> get() = backstackEntries.toList()

    private fun emitTopEntry() {
        mutableCurrentEntryStateFlow.value = backstackEntries.last()
    }

    override fun push(key: ScreenKey) {
        backstackEntries.add(createBackstackEntry(key))
        emitTopEntry()
    }

    override fun pop() {
        if (backstackEntries.size > 1) {
            val removed = backstackEntries.removeLast()
            removed.close()
            emitTopEntry()
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
