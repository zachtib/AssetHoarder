package com.zachtib.sampleapp

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import com.zachtib.lib.backstack.createNavigator
import com.zachtib.lib.log.Level
import com.zachtib.lib.log.Logger
import com.zachtib.lib.log.log
import com.zachtib.lib.log.logToConsole
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

@Composable
fun App() {
    log { "Another thing" }
    MaterialTheme {
        val backstack = remember { AppBackstack(HomeScreenKey) }
        val current: AppBackstackEntry by backstack.current.collectAsState()
        log(Level.VERBOSE) { Json.encodeToString(backstack) }

        AppNavigation(current, backstack.createNavigator())
    }
}

fun main() = application {
    Logger.logToConsole(minimumLogLevel = Level.VERBOSE)
    Window(onCloseRequest = ::exitApplication) {
        App()
    }
}
