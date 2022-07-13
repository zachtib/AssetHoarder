package com.zachtib.assets

import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import com.zachtib.assets.lib.state.StateHandle
import com.zachtib.assets.lib.state.state
import com.zachtib.assets.navigation.Navigator
import com.zachtib.assets.navigation.ProfileScreenKey
import com.zachtib.assets.viewmodel.ViewModel
import kotlinx.coroutines.launch

class HomeViewModel(
    private val state: StateHandle,
    private val navigator: Navigator,
) : ViewModel() {

    var counter: Int by state.state("counter", 0)
        private set

    fun onIncrementCounter() {
        counter++
    }

    fun onFirstButtonPressed() {
        launch { navigator.goToProfile(profileId = 1) }
    }

    fun onSecondButtonPressed() {
        launch { navigator.goToProfile(profileId = 2) }
    }

    fun onSettingsPressed() {
        launch { navigator.goToSettings() }
    }
}

class ProfileViewModel(
    private val profileKey: ProfileScreenKey,
    private val state: StateHandle,
    private val navigator: Navigator,
) : ViewModel() {

    val message = "Welcome to profile ${profileKey.profileId}"

    fun onGoBackPressed() {
        launch { navigator.back() }
    }
}

class SettingsViewModel(
    private val state: StateHandle,
    private val navigator: Navigator,
) : ViewModel() {

    fun onGoBackPressed() {
        launch { navigator.back() }
    }
}
