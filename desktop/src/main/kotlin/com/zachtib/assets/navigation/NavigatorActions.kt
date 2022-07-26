package com.zachtib.assets.navigation

import com.zachtib.lib.backstack.Navigator

typealias AppNavigator = Navigator<ScreenKey>

suspend fun AppNavigator.back() = perform {
    pop()
}

suspend fun AppNavigator.goHome() = perform {
    push(HomeScreenKey)
}
