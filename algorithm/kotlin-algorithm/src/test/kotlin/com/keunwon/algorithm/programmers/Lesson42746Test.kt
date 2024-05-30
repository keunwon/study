package com.keunwon.algorithm.programmers

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class Lesson42746Test : StringSpec({
    "case-1" {
        val numbers = intArrayOf(6, 10, 2)
        val actual = Lesson42746().solution(numbers)
        actual shouldBe "6210"
    }

    "case-2" {
        val numbers = intArrayOf(3, 30, 34, 5, 9)
        val actual = Lesson42746().solution(numbers)
        actual shouldBe "9534330"
    }
})
