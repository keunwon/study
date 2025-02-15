package com.keunwon.algorithm.baekjoon

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class Problem21609Test : StringSpec({
    "case-1" {
        val m = 3
        val map = arrayOf(
            intArrayOf(2, 2, -1, 3, 1),
            intArrayOf(3, 3, 2, 0, -1),
            intArrayOf(0, 0, 0, 1, 2),
            intArrayOf(-1, 3, 1, 3, 2),
            intArrayOf(0, 3, 2, 2, 1),
        )

        val actual = Problem21609().solution(m, map)

        actual shouldBe 77
    }

    "case-2" {
        val m = 4
        val map = arrayOf(
            intArrayOf(2, 3, 1, 0, -1, 2),
            intArrayOf(2, -1, 4, 1, 3, 3),
            intArrayOf(3, 0, 4, 2, 2, 1),
            intArrayOf(-1, 4, -1, 2, 3, 4),
            intArrayOf(3, -1, 4, 2, 0, 3),
            intArrayOf(1, 2, 2, 2, 2, 1),
        )

        val actual = Problem21609().solution(m, map)

        actual shouldBe 125
    }

    "case-3" {
        val m = 3
        val map = arrayOf(
            intArrayOf(1, 1, 1, 3),
            intArrayOf(3, 2, 3, 3),
            intArrayOf(1, 2, -1, 3),
            intArrayOf(-1, -1, 1, 1),
        )

        val actual = Problem21609().solution(m, map)

        actual shouldBe 33
    }
})
