package com.zachtib.assets

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import com.zachtib.assets.navigation.AppBackstack
import com.zachtib.assets.navigation.AppBackstackEntry
import com.zachtib.assets.navigation.HomeScreenKey

@Composable
fun App() {
    MaterialTheme {
        val backstack = remember { AppBackstack(HomeScreenKey) }
        val current: AppBackstackEntry by backstack.current.collectAsState()

        Text(text = "Hello, World")
    }
}

fun main() = application {
    Window(onCloseRequest = ::exitApplication) {
        App()
    }
}
