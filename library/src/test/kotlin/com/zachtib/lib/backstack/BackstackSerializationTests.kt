package com.zachtib.lib.backstack

import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import kotlin.test.*

class BackstackSerializationTests {
    @Test
    fun `test serializing a backstack with some elements`() {
        val backstack = Backstack<TestScreenKey>(home = HomeScreenKey)
        backstack.push(key = ProfileScreenKey(profileId = 12345))

        val serialized = Json.encodeToString(backstack)
        val expected =
            """[{"key":{"type":"home_screen"},"state":{}},{"key":{"type":"profile_screen","profileId":12345},"state":{}}]"""
        assertEquals(expected, serialized)
    }

    @Test
    fun `test serializing a backstack when an element has state`() {
        val backstack = Backstack<TestScreenKey>(home = HomeScreenKey)
        backstack.push(key = ProfileScreenKey(profileId = 12345))

        with(backstack.current.value) {
            state["name"] = "John Doe"
        }

        val serialized = Json.encodeToString(backstack)
        val expected =
            """[{"key":{"type":"home_screen"},"state":{}},{"key":{"type":"profile_screen","profileId":12345},"state":{"name":{"type":"string","value":"John Doe"}}}]"""
        assertEquals(expected, serialized)
    }

    @Test
    fun `test deserializing a backstack with state`() {
        val json =
            """[{"key":{"type":"home_screen"},"state":{}},{"key":{"type":"profile_screen","profileId":12345},"state":{"name":{"type":"string","value":"John Doe"}}}]"""

        val backstack: Backstack<TestScreenKey> = Json.decodeFromString(json)

        assertNotNull(backstack)
        assertEquals(2, backstack.contents.size)
        with(backstack.current.value) {
            assertEquals(ProfileScreenKey(profileId = 12345), key)
            assertEquals("John Doe", state["name"])
        }
    }
}
