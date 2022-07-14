package com.zachtib.sampleapp.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import com.zachtib.lib.backstack.Navigator
import com.zachtib.lib.log.Level.VERBOSE
import com.zachtib.lib.log.log
import com.zachtib.lib.statehandle.StateHandle
import com.zachtib.lib.statehandle.state
import com.zachtib.lib.viewmodels.ViewModel
import com.zachtib.sampleapp.ScreenKey
import com.zachtib.sampleapp.goToProfile
import com.zachtib.sampleapp.goToSettings
import kotlinx.coroutines.launch

class HomeViewModel(
    private val state: StateHandle,
    private val navigator: Navigator<ScreenKey>,
) : ViewModel() {

    init {
        log { "init" }
    }

    var counter: Int by state.state("counter", 0)
        private set

    fun onIncrementCounter() {
        log(VERBOSE) { "onIncrementCounter" }
        counter++
    }

    fun onFirstButtonPressed() {
        log(VERBOSE) { "onFirstButtonPressed" }
        launch { navigator.goToProfile(profileId = 1) }
    }

    fun onSecondButtonPressed() {
        log(VERBOSE) { "onSecondButtonPressed" }
        launch { navigator.goToProfile(profileId = 2) }
    }

    fun onSettingsPressed() {
        log(VERBOSE) { "onSettingsPressed" }
        launch { navigator.goToSettings() }
    }

    override fun close() {
        log { "close" }
        super.close()
    }
}
