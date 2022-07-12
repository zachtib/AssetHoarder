package com.zachtib.assets

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import com.zachtib.assets.battlemaps.BattleMapListView


@Composable
@Preview
fun App() {
    MaterialTheme {
        BattleMapListView()
    }
}

fun main() = application {
    Window(onCloseRequest = ::exitApplication) {
        App()
    }
}