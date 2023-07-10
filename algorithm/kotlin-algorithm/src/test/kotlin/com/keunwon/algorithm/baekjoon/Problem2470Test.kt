package com.keunwon.algorithm.baekjoon

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

internal class Problem2470Test : StringSpec({
    "case_01" {
        val arr = intArrayOf(-2, 4, -99, -1, 98)
        val actual = Problem2470().solution(arr)
        actual shouldBe intArrayOf(-99, 98)
    }
})
