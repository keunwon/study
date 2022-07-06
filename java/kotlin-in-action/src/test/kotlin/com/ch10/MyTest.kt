package com.ch10

import org.junit.Assert
import org.junit.Test

class MyTest {
    companion object {
        const val TEST_TIMEOUT = 100L
    }

    @Test fun testTrue() { Assert.assertTrue(true) }

    @Test(timeout = TEST_TIMEOUT) fun testMethod() {}
}
