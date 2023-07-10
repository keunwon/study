package com.keunwon.algorithm.baekjoon

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

internal class Problem2110Test : StringSpec({
    "case_01" {
        val c = 3
        val arr = intArrayOf(1, 2, 8, 4, 9)

        val actual = Problem2110().solution(c, arr)

        actual shouldBe 3
    }
})
