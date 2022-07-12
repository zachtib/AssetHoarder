package com.zachtib.assets.battlemaps

import com.zachtib.assets.viewmodel.ViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.stateIn

data class BattleMapListState(
    val isLoading: Boolean,
    val battleMaps: List<BattleMap>,
)

class BattleMapListViewModel(
    private val repository: BattleMapRepository,
) : ViewModel() {

    val state = flow {
        val maps = repository.getAll()
        emit(BattleMapListState(isLoading = false, battleMaps = maps))
    }.stateIn(
        this,
        started = SharingStarted.WhileSubscribed(),
        initialValue = BattleMapListState(true, emptyList())
    )
}