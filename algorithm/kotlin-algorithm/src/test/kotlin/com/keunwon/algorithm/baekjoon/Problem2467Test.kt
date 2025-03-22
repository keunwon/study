package com.keunwon.algorithm.baekjoon

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class Problem2467Test : StringSpec({
    "case-1" {
        val numbers = intArrayOf(-99, -2, -1, 4, 98)
        val actual = Problem2467().solution(numbers)
        actual shouldBe intArrayOf(-99, 98)
    }

    "case-2" {
        val numbers = intArrayOf(-100, -2, -1, 103)
        val actual = Problem2467().solution(numbers)
        actual shouldBe intArrayOf(-100, 103)
    }
})
