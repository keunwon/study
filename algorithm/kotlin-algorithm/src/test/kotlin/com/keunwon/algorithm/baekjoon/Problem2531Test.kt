package com.keunwon.algorithm.baekjoon

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

internal class Problem2531Test : StringSpec({
    "case_01" {
        val d = 30
        val k = 4
        val c = 30
        val arr = intArrayOf(7, 9, 7, 30, 2, 7, 9, 25)

        val actual = Problem2531().solution(d, k, c, arr)

        actual shouldBe 5
    }

    "case_02" {
        val d = 50
        val k = 4
        val c = 7
        val arr = intArrayOf(2, 7, 9, 25, 7, 9, 7, 30)

        val actual = Problem2531().solution(d, k, c, arr)

        actual shouldBe 4
    }
})
