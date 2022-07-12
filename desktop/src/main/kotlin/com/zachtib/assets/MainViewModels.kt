package com.zachtib.assets

import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import com.zachtib.assets.lib.state.StateHandle
import com.zachtib.assets.lib.state.state
import com.zachtib.assets.navigation.BackstackScope
import com.zachtib.assets.navigation.ProfileScreenKey
import com.zachtib.assets.navigation.SettingsScreenKey
import com.zachtib.assets.viewmodel.ViewModel

class HomeViewModel(
    private val state: StateHandle,
    private val navigator: BackstackScope,
) : ViewModel() {

    var counter: Int by state.state("counter", 0)
        private set

    fun onIncrementCounter() {
        counter++
    }

    fun onFirstButtonPressed() {
        navigator.push(ProfileScreenKey(profileId = 1))
    }

    fun onSecondButtonPressed() {
        navigator.push(ProfileScreenKey(profileId = 2))
    }

    fun onSettingsPressed() {
        navigator.push(SettingsScreenKey)
    }
}

class ProfileViewModel(
    private val profileKey: ProfileScreenKey,
    private val state: StateHandle,
    private val navigator: BackstackScope,
) : ViewModel() {

    val message = "Welcome to profile ${profileKey.profileId}"

    fun onGoBackPressed() {
        navigator.pop()
    }
}

class SettingsViewModel(
    private val state: StateHandle,
    private val navigator: BackstackScope,
) : ViewModel() {

    fun onGoBackPressed() {
        navigator.pop()
    }
}
