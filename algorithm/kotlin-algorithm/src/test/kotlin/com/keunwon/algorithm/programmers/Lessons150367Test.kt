package com.keunwon.algorithm.programmers

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

internal class Lessons150367Test : StringSpec({
    "case_01" {
        val numbers = longArrayOf(7, 42, 5)
        val actual = Lessons150367().solution(numbers)
        actual shouldBe intArrayOf(1, 1, 0)
    }

    "case_02" {
        val numbers = longArrayOf(63, 111, 95)
        val actual = Lessons150367().solution(numbers)
        actual shouldBe intArrayOf(1, 1, 0)
    }
})
