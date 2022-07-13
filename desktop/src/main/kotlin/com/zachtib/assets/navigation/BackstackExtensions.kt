package com.zachtib.assets.navigation

fun Backstack.createNavigator() = Navigator { action -> action() }
