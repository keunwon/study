package com.keunwon.algorithm.baekjoon

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class Problem21610Test : StringSpec({
    "case-1" {
        val board = arrayOf(
            intArrayOf(0, 0, 1, 0, 2),
            intArrayOf(2, 3, 2, 1, 0),
            intArrayOf(4, 3, 2, 9, 0),
            intArrayOf(1, 0, 2, 9, 0),
            intArrayOf(8, 8, 2, 1, 0),
        )
        val infos = arrayOf(
            intArrayOf(1, 3),
            intArrayOf(3, 4),
            intArrayOf(8, 1),
            intArrayOf(4, 8),
        )

        val actual = Problem21610().solution(board, infos)

        actual shouldBe 77
    }

    "case-2" {
        val board = arrayOf(
            intArrayOf(0, 0, 1, 0, 2),
            intArrayOf(2, 3, 2, 1, 0),
            intArrayOf(0, 0, 2, 0, 0),
            intArrayOf(1, 0, 2, 0, 0),
            intArrayOf(0, 0, 2, 1, 0),
        )
        val infos = arrayOf(
            intArrayOf(1, 9),
            intArrayOf(2, 8),
            intArrayOf(3, 7),
            intArrayOf(4, 6),
            intArrayOf(5, 5),
            intArrayOf(6, 4),
            intArrayOf(7, 3),
            intArrayOf(8, 2),
        )

        val actual = Problem21610().solution(board, infos)

        actual shouldBe 41
    }

    "case-3" {
        val board = arrayOf(
            intArrayOf(100, 100, 100, 100, 100),
            intArrayOf(100, 100, 100, 100, 100),
            intArrayOf(100, 100, 100, 100, 100),
            intArrayOf(100, 100, 100, 100, 100),
            intArrayOf(100, 100, 100, 100, 100),
        )
        val infos = arrayOf(
            intArrayOf(8, 1),
            intArrayOf(7, 1),
            intArrayOf(6, 1),
            intArrayOf(5, 1),
            intArrayOf(4, 1),
            intArrayOf(3, 1),
            intArrayOf(2, 1),
            intArrayOf(1, 1),
        )

        val actual = Problem21610().solution(board, infos)

        actual shouldBe 2657
    }
})
