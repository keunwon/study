package com.keunwon.algorithm.baekjoon

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

internal class Problem1806Test : StringSpec({
    "case_01" {
        val s = 15
        val arr = intArrayOf(5, 1, 3, 5, 10, 7, 4, 9, 2, 8)

        val actual = Problem1806().solution(s, arr)

        actual shouldBe 2
    }
})
