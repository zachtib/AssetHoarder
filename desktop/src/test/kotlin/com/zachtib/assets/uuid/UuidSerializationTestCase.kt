package com.zachtib.assets.uuid

import kotlinx.serialization.Serializable
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import kotlin.test.*

@Serializable
data class SampleSerializable(
    val id: UUID,
    val name: String,
    val description: String,
)

class UuidSerializationTestCase {

    @Test
    fun `test serializing a UUID`() {
        val uuid = UUID(UUID_STRING)
        assertNotNull(uuid)

        val expected = "\"$UUID_STRING\""
        val actual = Json.encodeToString(UUIDStringSerializer, uuid)
        assertEquals(expected, actual)
    }

    @Test
    fun `test serializing SampleSerializable`() {
        val value = SampleSerializable(
            id = UUID("55812c5b-518f-4678-a928-17545e4b0555")!!,
            name = "My Sample",
            description = "This is a sample item to be serialized",
        )
        val actual = Json.encodeToString(value)

        val expected = """{"id":"55812c5b-518f-4678-a928-17545e4b0555","name":"My Sample","description":"This is a sample item to be serialized"}"""
        assertEquals(expected, actual)
    }

    @Test
    fun `test deserializing SampleSerializable`() {
        val json = """{"id":"528716e6-4671-4dab-9ca7-2510037f7d9f","name":"Expected Value","description":"This is the expected value to be deserialized"}"""

        val actual = Json.decodeFromString<SampleSerializable>(json)

        val expected = SampleSerializable(
            id = UUID("528716e6-4671-4dab-9ca7-2510037f7d9f")!!,
            name = "Expected Value",
            description = "This is the expected value to be deserialized",
        )

        assertEquals(expected, actual)
    }

    companion object {
        const val UUID_STRING = "f9667a7c-ead2-45de-b53e-f781ec829548"
    }
}
