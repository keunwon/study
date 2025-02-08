package com.keunwon.algorithm.baekjoon

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class Problem14888Test : StringSpec({
    "case-1" {
        val numbers = intArrayOf(5, 6)
        val operators = intArrayOf(0, 0, 1, 0)

        val actual = Problem14888().solution(numbers, operators)

        actual shouldBe intArrayOf(30, 30)
    }

    "case-2" {
        val numbers = intArrayOf(3, 4, 5)
        val operators = intArrayOf(1, 0, 1, 0)

        val actual = Problem14888().solution(numbers, operators)

        actual shouldBe intArrayOf(35, 17)
    }

    "case-3" {
        val numbers = intArrayOf(1, 2, 3, 4, 5, 6)
        val operators = intArrayOf(2, 1, 1, 1)

        val actual = Problem14888().solution(numbers, operators)

        actual shouldBe intArrayOf(54, -24)
    }
})
