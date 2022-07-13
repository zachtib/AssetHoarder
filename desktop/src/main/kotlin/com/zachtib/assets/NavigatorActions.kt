package com.zachtib.assets

import com.zachtib.assets.navigation.HomeScreenKey
import com.zachtib.assets.navigation.Navigator
import com.zachtib.assets.navigation.ProfileScreenKey
import com.zachtib.assets.navigation.ScreenKey
import com.zachtib.assets.navigation.SettingsScreenKey

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
