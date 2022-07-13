package com.zachtib.assets.navigation

import com.zachtib.lib.backstack.Navigator

typealias AppNavigator = Navigator<ScreenKey>

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
