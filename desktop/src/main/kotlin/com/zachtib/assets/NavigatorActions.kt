package com.zachtib.assets

import com.zachtib.assets.navigation.HomeScreenKey
import com.zachtib.assets.navigation.ProfileScreenKey
import com.zachtib.assets.navigation.ScreenKey
import com.zachtib.assets.navigation.SettingsScreenKey
import com.zachtib.lib.backstack.Navigator

suspend fun Navigator<ScreenKey>.back() = perform {
    pop()
}

suspend fun Navigator<ScreenKey>.goHome() = perform {
    push(HomeScreenKey)
}

suspend fun Navigator<ScreenKey>.goToProfile(profileId: Long) = perform {
    push(ProfileScreenKey(profileId))
}

suspend fun Navigator<ScreenKey>.goToSettings() = perform {
    push(SettingsScreenKey)
}
