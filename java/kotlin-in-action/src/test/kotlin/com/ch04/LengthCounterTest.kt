package com.ch04

import io.kotest.core.spec.style.DescribeSpec
import io.kotest.inspectors.forAll
import io.kotest.matchers.shouldBe

internal class LengthCounterTest : DescribeSpec({

    describe("addWord") {
        context("문자열을 입력하면") {
            it("기본에 문자열 길이 + 입력된 문자의 길이의 합계를 반환한다") {
                listOf("Hi!", "!Hi").forAll { word ->
                    val lengthCounter = LengthCounter()

                    lengthCounter.addWord(prefix)
                    lengthCounter.addWord(word)
                    lengthCounter.counter shouldBe prefix.length + word.length
                }
            }
        }
    }
}) {
    companion object {
        const val prefix = "prefix"
    }
}
