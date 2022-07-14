package com.zachtib.sampleapp.settings

import com.zachtib.lib.backstack.Navigator
import com.zachtib.lib.log.Level
import com.zachtib.lib.log.log
import com.zachtib.lib.statehandle.StateHandle
import com.zachtib.lib.viewmodels.ViewModel
import com.zachtib.sampleapp.ScreenKey
import com.zachtib.sampleapp.back
import kotlinx.coroutines.launch

class SettingsViewModel(
    private val state: StateHandle,
    private val navigator: Navigator<ScreenKey>,
) : ViewModel() {

    init {
        log { "init" }
    }

    fun onGoBackPressed() {
        log(Level.VERBOSE) { "onGoBackPressed" }
        launch { navigator.back() }
    }

    override fun close() {
        log { "close" }
        super.close()
    }
}
