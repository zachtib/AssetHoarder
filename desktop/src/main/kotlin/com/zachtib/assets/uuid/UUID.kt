package com.zachtib.assets.uuid

interface UUID {
    val uuidString: String
    val bytes: ByteArray

    companion object
}
