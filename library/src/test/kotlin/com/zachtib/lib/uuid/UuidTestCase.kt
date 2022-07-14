package com.zachtib.lib.uuid

import kotlin.test.*

class UuidTestCase {

    @Test
    fun `test calling UUID() gets a random uuid`() {
        val uuid = UUID()

        assertIs<UUID>(uuid)
        assertNotNull(uuid)
        assertNotNull(uuid.uuidString)
    }

    @Test
    fun `test calling UUID() with valid uuidString returns a non-null UUID`() {
        val uuid: UUID? = UUID(uuidString = VALID_UUIDSTRING)
        assertNotNull(uuid)
    }

    @Test
    fun `test calling UUID() with invalid uuidString returns null`() {
        val uuid: UUID? = UUID(uuidString = INVALID_UUIDSTRING)
        assertNull(uuid)
    }

    @Test
    fun `test UUID equality for UUIDs created from same uuidString`() {
        val uuid1 = UUID(uuidString = VALID_UUIDSTRING)
        val uuid2 = UUID(uuidString = VALID_UUIDSTRING)

        assertTrue(uuid1 !== uuid2, "Two separate UUIDs should not be the same reference")
        assertEquals(uuid1, uuid2)
    }

    @Test
    fun `test that two UUIDs created from different strings are not equal`() {
        val uuid1 = UUID(uuidString = VALID_UUIDSTRING)
        val uuid2 = UUID(uuidString = OTHER_UUIDSTRING)

        assertTrue(uuid1 !== uuid2, "Two separate UUIDs should not be the same reference")
        assertNotEquals(uuid1, uuid2)
    }

    @Test
    fun `test generation of byteArray from UUID`() {
        val uuid = UUID(uuidString = BYTES_UUIDSTRING)

        assertNotNull(uuid)

        val actual = uuid.bytes

        assertContentEquals(UUID_BYTEARRAY, actual)
    }

    @Test
    fun `test creation of UUID from byteArray`() {
        val uuid = UUID(bytes = UUID_BYTEARRAY)

        assertNotNull(uuid)
        assertEquals(BYTES_UUIDSTRING, uuid.uuidString)
    }

    @Test
    fun `test hashCodes of two UUID created from the same string are equal`() {
        val uuid1 = UUID(uuidString = VALID_UUIDSTRING)
        val uuid2 = UUID(uuidString = VALID_UUIDSTRING)

        assertNotNull(uuid1)
        assertNotNull(uuid2)

        assertTrue(uuid1 !== uuid2, "Two separate UUIDs should not be the same reference")
        assertEquals(uuid1.hashCode(), uuid2.hashCode())
    }

    @Test
    fun `test hashCodes of two UUID created from different strings are not equal`() {
        val uuid1 = UUID(uuidString = VALID_UUIDSTRING)
        val uuid2 = UUID(uuidString = OTHER_UUIDSTRING)

        assertNotNull(uuid1)
        assertNotNull(uuid2)

        assertTrue(uuid1 !== uuid2, "Two separate UUIDs should not be the same reference")
        assertNotEquals(uuid1.hashCode(), uuid2.hashCode())
    }

    companion object {
        const val VALID_UUIDSTRING = "f9667a7c-ead2-45de-b53e-f781ec829548"
        const val OTHER_UUIDSTRING = "bcd1111b-d322-49c9-85ae-925dd1734108"
        const val INVALID_UUIDSTRING = "not-a-uuid"

        const val BYTES_UUIDSTRING = "bd9c7f32-8010-4cfe-97c0-82371e3276fa"
        val UUID_BYTEARRAY = byteArrayOf(-67, -100, 127, 50, -128, 16, 76, -2, -105, -64, -126, 55, 30, 50, 118, -6)
    }
}
