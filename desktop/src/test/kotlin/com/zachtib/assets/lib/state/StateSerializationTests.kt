package com.zachtib.assets.lib.state

import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import kotlin.test.*

class StateSerializationTests {

    @Test
    fun `test serializing an empty StateHandle`() {
        val stateHandle = StateHandle()
        val serialized = Json.encodeToString(stateHandle)

        assertEquals("{}", serialized)
    }

    @Test
    fun `test deserializing an empty StateHandle`() {
        val stateHandle: StateHandle = Json.decodeFromString("{}")

        assertNotNull(stateHandle)
        assertTrue(stateHandle.copyValues().isEmpty())
    }

    @Test
    fun `test serializing a StateHandle with values`() {
        val handle = StateHandle()
        handle["name"] = "John Doe"
        handle["age"] = 42

        val actual = Json.encodeToString(handle)

        assertEquals(JSON, actual)
    }

    @Test
    fun `test deserializing a StateHandle from json`() {
        val handle: StateHandle = Json.decodeFromString(JSON)

        assertNotNull(handle)

        val age: Int? = handle["age"]
        assertNotNull(age)
        assertEquals(42, age)

        val name: String? = handle["name"]
        assertNotNull(name)
        assertEquals("John Doe", name)
    }

    companion object {
        const val JSON = """{"name":{"type":"string","value":"John Doe"},"age":{"type":"int","value":42}}"""
    }
}
