package com.zachtib.assets

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import com.zachtib.assets.navigation.HomeScreenKey
import com.zachtib.assets.navigation.Navigator
import com.zachtib.assets.navigation.ProfileScreenKey
import com.zachtib.assets.navigation.ScreenKey
import com.zachtib.assets.navigation.SettingsScreenKey
import com.zachtib.assets.navigation.createNavigator
import com.zachtib.lib.backstack.Backstack
import com.zachtib.lib.backstack.BackstackEntry
import com.zachtib.lib.viewModel

@Composable
fun App() {
    MaterialTheme {
        val backstack = remember { Backstack<ScreenKey>(HomeScreenKey) }
        val current: BackstackEntry<ScreenKey> by backstack.current.collectAsState()
        AppContent(current, backstack.createNavigator())
    }
}

@Composable
fun AppContent(
    screen: BackstackEntry<ScreenKey>,
    navigator: Navigator<ScreenKey>,
) {
    when (screen.key) {
        HomeScreenKey -> HomeScreen(
            viewModel = screen.viewModel {
                HomeViewModel(screen.state, navigator)
            }
        )
        is ProfileScreenKey -> ProfileScreen(
            viewModel = screen.viewModel {
                ProfileViewModel(screen.key, screen.state, navigator)
            }
        )
        SettingsScreenKey -> SettingsScreen(
            viewModel = screen.viewModel {
                SettingsViewModel(screen.state, navigator)
            }
        )
    }
}

@Composable
fun HomeScreen(
    viewModel: HomeViewModel,
) {
    Column {
        Text(text = "Welcome to the home screen")
        Button(onClick = { viewModel.onFirstButtonPressed() }) {
            Text(text = "Go to Profile 1")
        }
        Button(onClick = { viewModel.onSecondButtonPressed() }) {
            Text(text = "Go to Profile 2")
        }
        Button(onClick = { viewModel.onSettingsPressed() }) {
            Text(text = "Go to Settings")
        }

        Text("You've clicked ${viewModel.counter} times")
        Button(onClick = { viewModel.onIncrementCounter() }) {
            Text(text = "Click me!")
        }
    }
}

@Composable
fun ProfileScreen(
    viewModel: ProfileViewModel,
) {
    Column {
        Text(text = viewModel.message)
        Button(onClick = { viewModel.onGoBackPressed() }) {
            Text(text = "Go back")
        }
    }
}

@Composable
fun SettingsScreen(
    viewModel: SettingsViewModel,
) {
    Column {
        Text(text = "Welcome to the settings screen")
        Button(onClick = { viewModel.onGoBackPressed() }) {
            Text(text = "Go back")
        }
    }
}

fun main() = application {
    Window(onCloseRequest = ::exitApplication) {
        App()
    }
}
