package com.keunwon.algorithm.baekjoon

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class Problem17484Test : StringSpec({
    "case-1" {
        val board = arrayOf(
            intArrayOf(5, 8, 5, 1),
            intArrayOf(3, 5, 8, 4),
            intArrayOf(9, 77, 65, 5),
            intArrayOf(2, 1, 5, 2),
            intArrayOf(5, 98, 1, 5),
            intArrayOf(4, 95, 67, 58),
        )

        val actual = Problem17484().solution(board)

        actual shouldBe 29
    }
})
