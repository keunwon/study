package com.keunwon.algorithm.baekjoon

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

internal class Problem2512Test : StringSpec({
    "case_01" {
        val arr = intArrayOf(120, 110, 140, 150)
        val m = 485

        val actual = Problem2512().solution(arr, m)

        actual shouldBe 127
    }

    "case_02" {
        val arr = intArrayOf(70, 80, 30, 40, 100)
        val m = 450

        val actual = Problem2512().solution(arr, m)

        actual shouldBe 100
    }
})
