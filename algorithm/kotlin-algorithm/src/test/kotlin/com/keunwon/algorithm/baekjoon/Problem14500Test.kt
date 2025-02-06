package com.keunwon.algorithm.baekjoon

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class Problem14500Test : StringSpec({
    "case-1" {
        val board = arrayOf(
            intArrayOf(1, 2, 3, 4, 5),
            intArrayOf(5, 4, 3, 2, 1),
            intArrayOf(2, 3, 4, 5, 6),
            intArrayOf(6, 5, 4, 3, 2),
            intArrayOf(1, 2, 1, 2, 1),
        )
        val actual = Problem14500().solution(board)
        actual shouldBe 19
    }

    "case-2" {
        val board = arrayOf(
            intArrayOf(1, 2, 3, 4, 5),
            intArrayOf(1, 2, 3, 4, 5),
            intArrayOf(1, 2, 3, 4, 5),
            intArrayOf(1, 2, 3, 4, 5),
        )
        val actual = Problem14500().solution(board)
        actual shouldBe 20
    }

    "case-3" {
        val board = arrayOf(
            intArrayOf(1, 2, 1, 2, 1, 2, 1, 2, 1, 2),
            intArrayOf(2, 1, 2, 1, 2, 1, 2, 1, 2, 1),
            intArrayOf(1, 2, 1, 2, 1, 2, 1, 2, 1, 2),
            intArrayOf(2, 1, 2, 1, 2, 1, 2, 1, 2, 1),
        )
        val actual = Problem14500().solution(board)
        actual shouldBe 7
    }
})
