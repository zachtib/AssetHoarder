package com.zachtib.assets.battlemaps

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.zachtib.assets.viewmodel.viewModel

@Composable
fun BattleMapListView() {
    val viewModel = viewModel {
        BattleMapListViewModel(JsonFileBattleMapRepository("battlemaps.json"))
    }
    val state by viewModel.state.collectAsState()
    println(state)

    BattleMapListContent(
        isLoading = state.isLoading,
        battleMaps = state.battleMaps,
    )
}

@Composable
fun BattleMapListContent(
    isLoading: Boolean,
    battleMaps: List<BattleMap>,
) {
    if (isLoading) {
        Text(text = "Loading...")
    } else {
        LazyColumn {
            items(battleMaps) { item: BattleMap ->
                Text(text = item.name)
            }
        }
    }
}