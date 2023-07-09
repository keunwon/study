package com.keunwon.algorithm.baekjoon

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

internal class Problem2264Test : StringSpec({
    "case_ 01" {
        val n = 4
        val arr = arrayOf(
            intArrayOf(0, 0),
            intArrayOf(2, 2),
            intArrayOf(2, 0),
            intArrayOf(0, 2),
        )

        val actual = Problem2264().solution(n, arr)

        actual shouldBe 1
    }

    "case_02" {
        val n = 4
        val arr = arrayOf(
            intArrayOf(0, 1),
            intArrayOf(1, 0),
            intArrayOf(3, 1),
            intArrayOf(4, 0),
        )

        val actual = Problem2264().solution(n, arr)

        actual shouldBe "NO"
    }

    "case_03" {
        val n = 5
        val arr = arrayOf(
            intArrayOf(1, 2),
            intArrayOf(2, 0),
            intArrayOf(3, 1),
            intArrayOf(4, 0),
            intArrayOf(5, 2),
        )

        val actual = Problem2264().solution(n, arr)

        actual shouldBe 3
    }
})
