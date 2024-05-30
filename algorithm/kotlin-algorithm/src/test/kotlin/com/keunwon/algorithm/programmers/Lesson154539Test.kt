package com.keunwon.algorithm.programmers

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class Lesson154539Test : StringSpec({
    "case-1" {
        val numbers = intArrayOf(2, 3, 3, 5)
        val actual = Lesson154539().solution(numbers)
        actual shouldBe intArrayOf(3, 5, 5, -1)
    }

    "case-2" {
        val numbers = intArrayOf(9, 1, 5, 3, 6, 2)
        val actual = Lesson154539().solution(numbers)
        actual shouldBe intArrayOf(-1, 5, 6, 6, -1, -1)
    }
})
