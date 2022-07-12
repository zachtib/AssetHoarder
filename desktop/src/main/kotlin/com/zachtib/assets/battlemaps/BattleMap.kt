package com.zachtib.assets.battlemaps

import com.zachtib.assets.uuid.UUID
import kotlinx.serialization.Serializable

@Serializable
data class BattleMap(
    val id: UUID,
    val name: String,
    val description: String,
    val filepath: String,
    val tags: Set<String>,
)
