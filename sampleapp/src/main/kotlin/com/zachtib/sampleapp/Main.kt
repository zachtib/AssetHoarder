package com.zachtib.sampleapp

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import com.zachtib.lib.backstack.createNavigator

@Composable
fun App() {
    MaterialTheme {
        val backstack = remember { AppBackstack(HomeScreenKey) }
        val current: AppBackstackEntry by backstack.current.collectAsState()

        AppNavigation(current, backstack.createNavigator())
    }
}

fun main() = application {
    Window(onCloseRequest = ::exitApplication) {
        App()
    }
}
