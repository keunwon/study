package com.keunwon.algorithm.baekjoon

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class Problem2668Test : StringSpec({
    "case-1" {
        val numbers = intArrayOf(3, 1, 1, 5, 5, 4, 6)
        val actual = Problem2668().solution(numbers)
        actual shouldBe intArrayOf(3, 1, 3, 5)
    }
})
