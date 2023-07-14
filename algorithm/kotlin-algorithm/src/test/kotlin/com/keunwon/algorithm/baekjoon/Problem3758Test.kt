package com.keunwon.algorithm.baekjoon

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

internal class Problem3758Test : StringSpec({
    "case_01" {
        val n = 3
        val k = 3
        val id = 1
        val arr = arrayOf(
            intArrayOf(1, 1, 30),
            intArrayOf(2, 3, 30),
            intArrayOf(1, 2, 40),
            intArrayOf(1, 2, 20),
            intArrayOf(3, 1, 70),
        )

        val actual = Problem3758().solution(n, k, id, arr)

        actual shouldBe 1
    }

    "case_02" {
        val n = 4
        val k = 4
        val id = 1
        val arr = arrayOf(
            intArrayOf(1, 1, 50),
            intArrayOf(2, 1, 20),
            intArrayOf(1, 1, 80),
            intArrayOf(3, 1, 0),
            intArrayOf(1, 2, 20),
            intArrayOf(2, 2, 10),
            intArrayOf(4, 3, 0),
            intArrayOf(2, 1, 0),
            intArrayOf(2, 2, 100),
            intArrayOf(1, 4, 20),
        )

        val actual = Problem3758().solution(n, k, id, arr)

        actual shouldBe 2
    }
})
