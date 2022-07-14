package com.zachtib.lib.closeable

import kotlin.test.*

class CloseableCollectionsTests {

    @Test
    fun `test closing a simple map`() {
        val first = SomeCloseable()
        val second = SomeCloseable()

        val map = mutableCloseableMapOf(
            "first" to first,
            "second" to second,
        )

        map.close()

        assertTrue(first.isClosed)
        assertTrue(second.isClosed)
    }

    @Test
    fun `test closing a simple map, checking values read back from it`() {
        val map = mutableCloseableMapOf(
            "first" to SomeCloseable(),
            "second" to SomeCloseable(),
        )

        map.close()

        val first = map["first"]
        val second = map["second"]

        assertNotNull(first)
        assertNotNull(second)

        assertTrue(first.isClosed)
        assertTrue(second.isClosed)
    }

    @Test
    fun `test all elements are closed even when map is copied`() {
        val first = SomeCloseable()
        val second = SomeCloseable()

        val map = mutableCloseableMapOf(
            "first" to first,
            "second" to second,
        )

        val copiedMap = map.toMutableMap()
        copiedMap.closeAll()

        assertTrue(first.isClosed)
        assertTrue(second.isClosed)
    }

    @Test
    fun `test a value is not closed when it is replaced`() {
        val original = SomeCloseable()
        val map = mutableCloseableMapOf("key" to original)

        assertFalse(original.isClosed)

        val replacement = SomeCloseable()

        val removed = map.put("key", replacement)

        assertNotNull(removed)
        assertFalse(original.isClosed)
        assertFalse(removed.isClosed)

        map.close()
        assertTrue(replacement.isClosed)
        assertFalse(original.isClosed)
        assertFalse(removed.isClosed)
    }

    inner class SomeCloseable : Closeable {
        var isClosed: Boolean = false
            private set

        override fun close() {
            isClosed = true
        }
    }
}
