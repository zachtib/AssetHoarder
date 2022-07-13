package com.zachtib.assets.navigation

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
sealed class ScreenKey

@Serializable
@SerialName("home_screen")
object HomeScreenKey : ScreenKey()
