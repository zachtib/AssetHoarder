package com.zachtib.assets

import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import com.zachtib.assets.navigation.ProfileScreenKey
import com.zachtib.assets.navigation.ScreenKey
import com.zachtib.lib.backstack.Navigator
import com.zachtib.lib.statehandle.StateHandle
import com.zachtib.lib.statehandle.state
import com.zachtib.lib.viewmodels.ViewModel
import kotlinx.coroutines.launch

class HomeViewModel(
    private val state: StateHandle,
    private val navigator: Navigator<ScreenKey>,
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
    private val navigator: Navigator<ScreenKey>,
) : ViewModel() {

    val message = "Welcome to profile ${profileKey.profileId}"

    fun onGoBackPressed() {
        launch { navigator.back() }
    }
}

class SettingsViewModel(
    private val state: StateHandle,
    private val navigator: Navigator<ScreenKey>,
) : ViewModel() {

    fun onGoBackPressed() {
        launch { navigator.back() }
    }
}
