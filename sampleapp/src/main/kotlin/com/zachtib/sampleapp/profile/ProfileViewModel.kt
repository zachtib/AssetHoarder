package com.zachtib.sampleapp.profile

import com.zachtib.lib.backstack.Navigator
import com.zachtib.lib.log.Level
import com.zachtib.lib.log.log
import com.zachtib.lib.statehandle.StateHandle
import com.zachtib.lib.viewmodels.ViewModel
import com.zachtib.sampleapp.ProfileScreenKey
import com.zachtib.sampleapp.ScreenKey
import com.zachtib.sampleapp.back
import kotlinx.coroutines.launch

class ProfileViewModel(
    private val profileKey: ProfileScreenKey,
    private val state: StateHandle,
    private val navigator: Navigator<ScreenKey>,
) : ViewModel() {

    init {
        log { "init: $profileKey" }
    }

    val message = "Welcome to profile ${profileKey.profileId}"

    fun onGoBackPressed() {
        log(Level.VERBOSE) { "onGoBackPressed" }
        launch { navigator.back() }
    }

    override fun close() {
        log { "close" }
        super.close()
    }
}
