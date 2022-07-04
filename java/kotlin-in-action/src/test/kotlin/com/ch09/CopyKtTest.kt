package com.ch09

import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.collections.shouldContainExactly

internal class CopyKtTest : DescribeSpec({

    describe("copyData2") {
        it("리스트를 복사합니다") {
            val anyItems = mutableListOf<Any>()

            copyData2(ints, anyItems)
            copyData2(strings, anyItems)

            anyItems shouldContainExactly (ints + strings)
        }
    }
}) {
    companion object {
        val ints = mutableListOf(1, 2, 3)
        val strings = mutableListOf("apple", "banana", "cat")
    }
}
