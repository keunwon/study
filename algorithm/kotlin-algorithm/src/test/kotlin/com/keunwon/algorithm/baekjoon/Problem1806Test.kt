package com.keunwon.algorithm.baekjoon

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class Problem1806Test : StringSpec({
    "case-1" {
        val s = 15
        val numbers = intArrayOf(5, 1, 3, 5, 10, 7, 4, 9, 2, 8)

        val actual = Problem1806().solution(s, numbers)

        actual shouldBe 2
    }
})
