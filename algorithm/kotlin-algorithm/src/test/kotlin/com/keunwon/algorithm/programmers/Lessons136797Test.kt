package com.keunwon.algorithm.programmers

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

internal class Lessons136797Test : StringSpec({
    "case_01" {
        val numbers = "1756"
        val actual = Lessons136797().solution(numbers)
        actual shouldBe 10
    }

    "case_02" {
        val numbers = "5123"
        val actual = Lessons136797().solution(numbers)
        actual shouldBe 8
    }
})
