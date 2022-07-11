package com.zachtib.assets.uuid

import java.nio.BufferUnderflowException
import java.nio.ByteBuffer

@JvmInline
internal value class JvmUUID private constructor(private val value: java.util.UUID) : UUID {
    override val uuidString: String
        get() = value.toString()

    override val bytes: ByteArray
        get() = ByteArray(16).buffer {
            putLong(value.mostSignificantBits)
            putLong(value.leastSignificantBits)

            array()
        }

    companion object : UUID.Platform {

        /**
         * Wrap this [ByteArray] in a [ByteBuffer], then return the result
         * of [block] on that buffer.
         */
        private fun <R> ByteArray.buffer(block: ByteBuffer.() -> R): R {
            return ByteBuffer.wrap(this).block()
        }

        override fun randomUUID(): UUID {
            val javaUUID = java.util.UUID.randomUUID()
            return JvmUUID(value = javaUUID)
        }

        override fun fromString(uuidString: String): UUID? {
            return try {
                val javaUUID = java.util.UUID.fromString(uuidString)
                JvmUUID(javaUUID)
            } catch (e: IllegalArgumentException) {
                null
            }
        }

        override fun fromByteArray(bytes: ByteArray): UUID? {
            return try {
                val javaUUID = bytes.buffer {
                    val high = long
                    val low = long
                    java.util.UUID(high, low)
                }

                JvmUUID(javaUUID)
            } catch (e: BufferUnderflowException) {
                null
            }
        }
    }
}
