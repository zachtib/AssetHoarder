package com.zachtib.lib.backstack

import com.zachtib.assets.lib.state.StateHandle
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.serialization.Serializable

@Serializable(with = BackstackSerializer::class)
class Backstack<K> internal constructor(
    initialBackstack: List<BackstackEntry<K>>,
) : BackstackScope<K> {

    private val backstackEntries: MutableList<BackstackEntry<K>>

    private val mutableCurrentEntryStateFlow: MutableStateFlow<BackstackEntry<K>>

    val current: StateFlow<BackstackEntry<K>>
        get() = mutableCurrentEntryStateFlow.asStateFlow()

    init {
        require(initialBackstack.isNotEmpty()) {
            "Can't initialize a Backstack with no ScreenKeys"
        }
        backstackEntries = initialBackstack.toMutableList()
        mutableCurrentEntryStateFlow = MutableStateFlow(backstackEntries.last())
    }

    companion object {
        private fun <K> createBackstackEntry(key: K): BackstackEntry<K> {
            return BackstackEntry(
                key = key,
                state = StateHandle()
            )
        }
    }

    constructor(home: K) : this(listOf(createBackstackEntry(home)))

    internal val contents: List<BackstackEntry<K>> get() = backstackEntries.toList()

    private fun emitTopEntry() {
        mutableCurrentEntryStateFlow.value = backstackEntries.last()
    }

    override fun push(key: K) {
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
