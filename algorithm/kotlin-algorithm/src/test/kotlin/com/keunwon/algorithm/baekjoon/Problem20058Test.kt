package com.keunwon.algorithm.baekjoon

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class Problem20058Test : StringSpec({
    "case-1" {
        val board = arrayOf(
            intArrayOf(1, 2, 3, 4, 5, 6, 7, 8),
            intArrayOf(8, 7, 6, 5, 4, 3, 2, 1),
            intArrayOf(1, 2, 3, 4, 5, 6, 7, 8),
            intArrayOf(8, 7, 6, 5, 4, 3, 2, 1),
            intArrayOf(1, 2, 3, 4, 5, 6, 7, 8),
            intArrayOf(8, 7, 6, 5, 4, 3, 2, 1),
            intArrayOf(1, 2, 3, 4, 5, 6, 7, 8),
            intArrayOf(8, 7, 6, 5, 4, 3, 2, 1),
        )
        val magics = intArrayOf(1)

        val actual = Problem20058().solution(board, magics)

        actual shouldBe intArrayOf(284, 64)
    }

    "case-2" {
        val board = arrayOf(
            intArrayOf(1, 2, 3, 4, 5, 6, 7, 8),
            intArrayOf(8, 7, 6, 5, 4, 3, 2, 1),
            intArrayOf(1, 2, 3, 4, 5, 6, 7, 8),
            intArrayOf(8, 7, 6, 5, 4, 3, 2, 1),
            intArrayOf(1, 2, 3, 4, 5, 6, 7, 8),
            intArrayOf(8, 7, 6, 5, 4, 3, 2, 1),
            intArrayOf(1, 2, 3, 4, 5, 6, 7, 8),
            intArrayOf(8, 7, 6, 5, 4, 3, 2, 1),
        )
        val magics = intArrayOf(1, 2)

        val actual = Problem20058().solution(board, magics)

        actual shouldBe intArrayOf(280, 64)
    }

    "case-3" {
        val board = arrayOf(
            intArrayOf(1, 2, 3, 4, 5, 6, 7, 8),
            intArrayOf(8, 7, 6, 5, 4, 3, 2, 1),
            intArrayOf(1, 2, 3, 4, 5, 6, 7, 8),
            intArrayOf(8, 7, 6, 5, 4, 3, 2, 1),
            intArrayOf(1, 2, 3, 4, 5, 6, 7, 8),
            intArrayOf(8, 7, 6, 5, 4, 3, 2, 1),
            intArrayOf(1, 2, 3, 4, 5, 6, 7, 8),
            intArrayOf(8, 7, 6, 5, 4, 3, 2, 1),
        )
        val magics = intArrayOf(1, 2, 0, 3, 2)

        val actual = Problem20058().solution(board, magics)

        actual shouldBe intArrayOf(268, 64)
    }

    "case-4" {
        val board = arrayOf(
            intArrayOf(1, 2, 3, 4, 5, 6, 7, 8),
            intArrayOf(8, 7, 6, 5, 4, 3, 2, 1),
            intArrayOf(1, 2, 3, 4, 5, 6, 7, 8),
            intArrayOf(8, 7, 6, 5, 4, 3, 2, 1),
            intArrayOf(1, 2, 3, 4, 5, 6, 7, 8),
            intArrayOf(8, 7, 6, 5, 4, 3, 2, 1),
            intArrayOf(1, 2, 3, 4, 5, 6, 7, 8),
            intArrayOf(8, 7, 6, 5, 4, 3, 2, 1),
        )
        val magics = intArrayOf(1, 2, 0, 3, 2, 1, 2, 3, 2, 3)

        val actual = Problem20058().solution(board, magics)

        actual shouldBe intArrayOf(248, 62)
    }

    "case-5" {
        val board = arrayOf(
            intArrayOf(1, 2, 3, 4, 5, 6, 7, 8),
            intArrayOf(8, 7, 6, 5, 4, 3, 2, 1),
            intArrayOf(1, 2, 3, 4, 5, 6, 7, 8),
            intArrayOf(8, 7, 6, 5, 4, 3, 2, 1),
            intArrayOf(1, 2, 3, 4, 5, 6, 7, 8),
            intArrayOf(8, 7, 6, 5, 4, 3, 2, 1),
            intArrayOf(1, 2, 3, 4, 5, 6, 7, 8),
            intArrayOf(8, 7, 6, 5, 4, 3, 2, 1),
        )
        val magics = intArrayOf(1, 2, 3, 1, 2, 3, 1, 2, 3, 1)

        val actual = Problem20058().solution(board, magics)

        actual shouldBe intArrayOf(246, 60)
    }

    "case-6" {
        val board = arrayOf(
            intArrayOf(1, 0, 3, 4, 5, 6, 7, 0),
            intArrayOf(8, 0, 6, 5, 4, 3, 2, 1),
            intArrayOf(1, 2, 0, 4, 5, 6, 7, 0),
            intArrayOf(8, 7, 6, 5, 4, 3, 2, 1),
            intArrayOf(1, 2, 3, 4, 0, 6, 7, 0),
            intArrayOf(8, 7, 0, 5, 4, 3, 2, 1),
            intArrayOf(1, 2, 3, 4, 5, 6, 7, 0),
            intArrayOf(0, 7, 0, 5, 4, 3, 2, 1),
        )
        val magics = intArrayOf(1, 2, 3, 1, 2, 3, 1, 2, 3, 1)

        val actual = Problem20058().solution(board, magics)

        actual shouldBe intArrayOf(37, 9)
    }
})
