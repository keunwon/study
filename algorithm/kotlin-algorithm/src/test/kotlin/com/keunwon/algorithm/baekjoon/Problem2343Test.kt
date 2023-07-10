package com.keunwon.algorithm.baekjoon

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

internal class Problem2343Test : StringSpec({
    "case_01" {
        val arr = intArrayOf(1, 2, 3, 4, 5, 6, 7, 8, 9)
        val m = 3

        val actual = Problem2343().solution(m, arr)

        actual shouldBe 17
    }
})
