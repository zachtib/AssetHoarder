package com.zachtib.assets

import com.zachtib.assets.navigation.HomeScreenKey
import com.zachtib.assets.navigation.Navigator
import com.zachtib.assets.navigation.ProfileScreenKey
import com.zachtib.assets.navigation.SettingsScreenKey

suspend fun Navigator.back() = perform {
    pop()
}

suspend fun Navigator.goHome() = perform {
    push(HomeScreenKey)
}

suspend fun Navigator.goToProfile(profileId: Long) = perform {
    push(ProfileScreenKey(profileId))
}

suspend fun Navigator.goToSettings() = perform {
    push(SettingsScreenKey)
}
