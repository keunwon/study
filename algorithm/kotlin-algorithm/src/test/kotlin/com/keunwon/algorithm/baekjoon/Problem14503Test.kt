package com.keunwon.algorithm.baekjoon

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class Problem14503Test : StringSpec({
    "case-1" {
        val r = 1
        val c = 1
        val d = 0
        val board = arrayOf(
            intArrayOf(1, 1, 0),
            intArrayOf(1, 1, 1),
            intArrayOf(1, 0, 1),
            intArrayOf(1, 1, 1),
        )

        val actual = Problem14503().solution(r, c, d, board)

        actual shouldBe 1
    }

    "case-2" {
        val r = 7
        val c = 4
        val d = 0
        val board = arrayOf(
            intArrayOf(1, 1, 1, 1, 1, 1, 1, 1, 1, 1),
            intArrayOf(1, 0, 0, 0, 0, 0, 0, 0, 0, 1),
            intArrayOf(1, 0, 0, 0, 1, 1, 1, 1, 0, 1),
            intArrayOf(1, 0, 0, 1, 1, 0, 0, 0, 0, 1),
            intArrayOf(1, 0, 1, 1, 0, 0, 0, 0, 0, 1),
            intArrayOf(1, 0, 0, 0, 0, 0, 0, 0, 0, 1),
            intArrayOf(1, 0, 0, 0, 0, 0, 0, 1, 0, 1),
            intArrayOf(1, 0, 0, 0, 0, 0, 1, 1, 0, 1),
            intArrayOf(1, 0, 0, 0, 0, 0, 1, 1, 0, 1),
            intArrayOf(1, 0, 0, 0, 0, 0, 0, 0, 0, 1),
            intArrayOf(1, 1, 1, 1, 1, 1, 1, 1, 1, 1),
        )

        val actual = Problem14503().solution(r, c, d, board)

        actual shouldBe 57
    }
})
