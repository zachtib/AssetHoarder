package com.zachtib.lib.uuid

import kotlinx.serialization.Serializable

/**
 * Multiplatform friendly abstraction for UUID.
 *
 * This interface is written in a slightly roundabout way to enable using it in a future
 * kotlin-multiplatform project. The structure mirrors expect/actual from Kotlin-MP, and
 * uses the Platform interface to define the actual implementation. For now, there's only
 * one: [JvmUUID].
 */
@Serializable(with = UUIDStringSerializer::class)
interface UUID {
    val uuidString: String
    val bytes: ByteArray

    interface Platform {
        fun randomUUID(): UUID
        fun fromString(uuidString: String): UUID?
        fun fromByteArray(bytes: ByteArray): UUID?
    }

    companion object : Platform {
        operator fun invoke(): UUID = randomUUID()
        operator fun invoke(uuidString: String) = fromString(uuidString)
        operator fun invoke(bytes: ByteArray) = fromByteArray(bytes)

        private val delegate: Platform = JvmUUID

        override fun randomUUID() = delegate.randomUUID()
        override fun fromString(uuidString: String) = delegate.fromString(uuidString)
        override fun fromByteArray(bytes: ByteArray) = delegate.fromByteArray(bytes)
    }
}
