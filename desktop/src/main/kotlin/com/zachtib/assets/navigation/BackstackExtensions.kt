package com.zachtib.assets.navigation

import com.zachtib.lib.backstack.Backstack

fun <K> Backstack<K>.createNavigator() = Navigator<K> { action -> action() }
