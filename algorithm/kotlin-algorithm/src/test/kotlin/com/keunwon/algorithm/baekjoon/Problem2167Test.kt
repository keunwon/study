package com.keunwon.algorithm.baekjoon

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

internal class Problem2167Test : StringSpec({
    "case" {
        val arr = arrayOf(
            intArrayOf(1, 2, 4),
            intArrayOf(8, 16, 32),
        )
        val rnages = arrayOf(
            intArrayOf(1, 1, 2, 3),
            intArrayOf(1, 2, 1, 2),
            intArrayOf(1, 3, 2, 3),
        )

        val actual = Problem2167().solution(arr, rnages)

        actual shouldBe intArrayOf(63, 2, 36)
    }
})
