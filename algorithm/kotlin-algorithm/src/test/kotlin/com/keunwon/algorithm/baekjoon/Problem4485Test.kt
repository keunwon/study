package com.keunwon.algorithm.baekjoon

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class Problem4485Test : StringSpec({
    "case-1" {
        val board = arrayOf(
            intArrayOf(5, 5, 4),
            intArrayOf(3, 9, 1),
            intArrayOf(3, 2, 7),
        )
        val actual = Problem4485().solution(board)
        actual shouldBe 20
    }

    "case-2" {
        val board = arrayOf(
            intArrayOf(3, 7, 2, 0, 1),
            intArrayOf(2, 8, 0, 9, 1),
            intArrayOf(1, 2, 1, 8, 1),
            intArrayOf(9, 8, 9, 2, 0),
            intArrayOf(3, 6, 5, 1, 5),
        )
        val actual = Problem4485().solution(board)
        actual shouldBe 19
    }

    "case-3" {
        val board = arrayOf(
            intArrayOf(9, 0, 5, 1, 1, 5, 3),
            intArrayOf(4, 1, 2, 1, 6, 5, 3),
            intArrayOf(0, 7, 6, 1, 6, 8, 5),
            intArrayOf(1, 1, 7, 8, 3, 2, 3),
            intArrayOf(9, 4, 0, 7, 6, 4, 1),
            intArrayOf(5, 8, 3, 2, 4, 8, 3),
            intArrayOf(7, 4, 8, 4, 8, 3, 4),
        )
        val actual = Problem4485().solution(board)
        actual shouldBe 36
    }
})
