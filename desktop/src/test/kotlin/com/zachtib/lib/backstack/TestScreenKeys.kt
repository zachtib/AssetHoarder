package com.zachtib.lib.backstack

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
sealed class TestScreenKey

@Serializable
@SerialName("home_screen")
object HomeScreenKey : TestScreenKey()

@Serializable
@SerialName("profile_screen")
data class ProfileScreenKey(val profileId: Long) : TestScreenKey()

@Serializable
@SerialName("settings_screen")
object SettingsScreenKey : TestScreenKey()
