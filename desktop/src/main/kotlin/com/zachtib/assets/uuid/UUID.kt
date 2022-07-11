package com.zachtib.assets.uuid

import kotlinx.serialization.Serializable

@Serializable(with = UUIDSerializer::class)
interface UUID {
    val uuidString: String
    val bytes: ByteArray

    companion object
}
