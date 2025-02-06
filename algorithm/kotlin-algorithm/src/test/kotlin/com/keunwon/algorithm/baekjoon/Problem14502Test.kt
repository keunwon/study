package com.keunwon.algorithm.baekjoon

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class Problem14502Test : StringSpec({
    "case-1" {
        val board = arrayOf(
            intArrayOf(2, 0, 0, 0, 1, 1, 0),
            intArrayOf(0, 0, 1, 0, 1, 2, 0),
            intArrayOf(0, 1, 1, 0, 1, 0, 0),
            intArrayOf(0, 1, 0, 0, 0, 0, 0),
            intArrayOf(0, 0, 0, 0, 0, 1, 1),
            intArrayOf(0, 1, 0, 0, 0, 0, 0),
            intArrayOf(0, 1, 0, 0, 0, 0, 0),
        )
        val actual = Problem14502().solution(board)
        actual shouldBe 27
    }

    "case-2" {
        val board = arrayOf(
            intArrayOf(0, 0, 0, 0, 0, 0),
            intArrayOf(1, 0, 0, 0, 0, 2),
            intArrayOf(1, 1, 1, 0, 0, 2),
            intArrayOf(0, 0, 0, 0, 0, 2),
        )
        val actual = Problem14502().solution(board)
        actual shouldBe 9
    }

    "case-3" {
        val board = arrayOf(
            intArrayOf(2, 0, 0, 0, 0, 0, 0, 2),
            intArrayOf(2, 0, 0, 0, 0, 0, 0, 2),
            intArrayOf(2, 0, 0, 0, 0, 0, 0, 2),
            intArrayOf(2, 0, 0, 0, 0, 0, 0, 2),
            intArrayOf(2, 0, 0, 0, 0, 0, 0, 2),
            intArrayOf(0, 0, 0, 0, 0, 0, 0, 0),
            intArrayOf(0, 0, 0, 0, 0, 0, 0, 0),
            intArrayOf(0, 0, 0, 0, 0, 0, 0, 0),
        )
        val actual = Problem14502().solution(board)
        actual shouldBe 3
    }
})
