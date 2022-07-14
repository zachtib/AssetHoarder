package com.zachtib.sampleapp

import androidx.compose.runtime.Composable
import com.zachtib.lib.backstack.Backstack
import com.zachtib.lib.backstack.BackstackEntry
import com.zachtib.lib.backstack.Navigator
import com.zachtib.lib.log.log
import com.zachtib.lib.viewModel
import com.zachtib.sampleapp.home.HomeScreen
import com.zachtib.sampleapp.home.HomeViewModel
import com.zachtib.sampleapp.profile.ProfileScreen
import com.zachtib.sampleapp.profile.ProfileViewModel
import com.zachtib.sampleapp.settings.SettingsScreen
import com.zachtib.sampleapp.settings.SettingsViewModel
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

typealias AppBackstack = Backstack<ScreenKey>
typealias AppBackstackEntry = BackstackEntry<ScreenKey>
typealias AppNavigator = Navigator<ScreenKey>

@Composable
fun AppNavigation(
    screen: AppBackstackEntry,
    navigator: AppNavigator,
) {
    log { "Screen: " +  Json.encodeToString(screen) }
    when (val key = screen.key) {
        HomeScreenKey -> HomeScreen(
            viewModel = screen.viewModel {
                HomeViewModel(screen.state, navigator)
            }
        )
        is ProfileScreenKey -> ProfileScreen(
            viewModel = screen.viewModel {
                ProfileViewModel(key, screen.state, navigator)
            }
        )
        SettingsScreenKey -> SettingsScreen(
            viewModel = screen.viewModel {
                SettingsViewModel(screen.state, navigator)
            }
        )
    }
}

suspend fun AppNavigator.back() = perform {
    pop()
}

suspend fun AppNavigator.goHome() = perform {
    push(HomeScreenKey)
}

suspend fun AppNavigator.goToProfile(profileId: Long) = perform {
    push(ProfileScreenKey(profileId))
}

suspend fun AppNavigator.goToSettings() = perform {
    push(SettingsScreenKey)
}
