package com.keunwon.algorithm.baekjoon

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

internal class Problem11660Test : StringSpec({
    "case" {
        val arr = arrayOf(
            intArrayOf(1, 2, 3, 4),
            intArrayOf(2, 3, 4, 5),
            intArrayOf(3, 4, 5, 6),
            intArrayOf(4, 5, 6, 7),
        )
        val rages = arrayOf(
            intArrayOf(2, 2, 3, 4),
            intArrayOf(3, 4, 3, 4),
            intArrayOf(1, 1, 4, 4),
        )

        val actual = Problem11660().solution(arr, rages)

        actual shouldBe intArrayOf(27, 6, 64)
    }

    "case_02" {
        val arr = arrayOf(
            intArrayOf(1, 2),
            intArrayOf(3, 4),
        )
        val rages = arrayOf(
            intArrayOf(1, 1, 1, 1),
            intArrayOf(1, 2, 1, 2),
            intArrayOf(2, 1, 2, 1),
            intArrayOf(2, 2, 2, 2),
        )

        val actual = Problem11660().solution(arr, rages)

        actual shouldBe intArrayOf(1, 2, 3, 4)
    }
})
