package com.keunwon.algorithm.programmers

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class Lesson150367Test : StringSpec({
    "case-1" {
        val numbers = longArrayOf(7, 42, 5)
        val actual = Lesson150367().solution(numbers)
        actual shouldBe intArrayOf(1, 1, 0)
    }

    "case-2" {
        val numbers = longArrayOf(63, 111, 95)
        val actual = Lesson150367().solution(numbers)
        actual shouldBe intArrayOf(1, 1, 0)
    }
})
