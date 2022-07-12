package com.zachtib.assets.navigation

import com.zachtib.assets.lib.state.StateHandle
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.junit.Test
import kotlin.test.*

class BackstackEntrySerializationTests {
    @Test
    fun `test serializing a BackstackEntry`() {
        val key = ProfileScreenKey(profileId = 12345)
        val state = StateHandle()
        val entry = BackstackEntry(key, state)

        val actual = Json.encodeToString(entry)
        val expected = """{"key":{"type":"profile_screen","profileId":12345},"state":{}}"""
        assertEquals(expected, actual)
    }

    @Test
    fun `test serializing a BackstackEntry with state`() {
        val key = ProfileScreenKey(profileId = 12345)
        val state = StateHandle()
        val entry = BackstackEntry(key, state)

        state["name"] = "John Doe"

        val actual = Json.encodeToString(entry)
        val expected = """{"key":{"type":"profile_screen","profileId":12345},"state":{"name":{"type":"string","value":"John Doe"}}}"""
        assertEquals(expected, actual)
    }

    @Test
    fun `test deserializing a BackstackEntry`() {
        val json = """{"key":{"type":"profile_screen","profileId":12345},"state":{}}"""
        val entry: BackstackEntry = Json.decodeFromString(json)

        assertNotNull(entry)
        assertEquals(ProfileScreenKey(profileId = 12345), entry.key)
        assertTrue(entry.state.copyValues().isEmpty())
    }

    @Test
    fun `test deserializing a BackstackEntry with state`() {
        val json = """{"key":{"type":"profile_screen","profileId":12345},"state":{"name":{"type":"string","value":"John Doe"}}}"""
        val entry: BackstackEntry = Json.decodeFromString(json)

        assertNotNull(entry)
        assertEquals(ProfileScreenKey(profileId = 12345), entry.key)
        assertEquals("John Doe", entry.state["name"])
    }
}
