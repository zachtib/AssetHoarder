package com.zachtib.assets.navigation

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName("home_screen")
object HomeScreenKey : ScreenKey()

@Serializable
@SerialName("profile_screen")
data class ProfileScreenKey(val profileId: Long) : ScreenKey()

@Serializable
@SerialName("settings_screen")
object SettingsScreenKey : ScreenKey()
