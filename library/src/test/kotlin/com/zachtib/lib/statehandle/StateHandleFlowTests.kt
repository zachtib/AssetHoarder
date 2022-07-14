package com.zachtib.lib.statehandle

import app.cash.turbine.test
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.runTest
import kotlin.test.*

@OptIn(ExperimentalCoroutinesApi::class)
class StateHandleFlowTests {
    @Test
    fun `test a Flow with no value should not emit`() = runTest {
        val handle = StateHandle()
        val flow = handle.getFlow<Int>("age")
        flow.test {
            this.expectNoEvents()
        }
    }

    @Test
    fun `test a Flow should emit a value when set is called`() = runTest {
        val handle = StateHandle()
        val flow = handle.getFlow<Int>("age")
        launch {
            delay(500)
            handle["age"] = 42
        }
        flow.test {
            assertEquals(42, awaitItem())
        }
    }

    @Test
    fun `test a Flow should emit values over time as set is called`() = runTest {
        val handle = StateHandle()
        val flow = handle.getFlow<Int>("age")
        launch {
            delay(500)
            handle["age"] = 42
            delay(500)
            handle["age"] = 100
            delay(500)
            handle["age"] = 50
        }
        flow.test {
            assertEquals(42, awaitItem())
            assertEquals(100, awaitItem())
            assertEquals(50, awaitItem())
        }
    }
}
