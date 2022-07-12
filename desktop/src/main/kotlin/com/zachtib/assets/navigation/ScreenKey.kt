package com.zachtib.assets.navigation

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
sealed class ScreenKey

@Serializable
@SerialName("profile_screen")
data class ProfileScreenKey(val profileId: Long) : ScreenKey()