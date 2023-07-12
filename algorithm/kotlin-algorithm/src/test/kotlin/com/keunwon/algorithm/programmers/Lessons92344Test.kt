package com.keunwon.algorithm.programmers

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

internal class Lessons92344Test : StringSpec({
    "case_01" {
        val board = arrayOf(
            intArrayOf(5, 5, 5, 5, 5),
            intArrayOf(5, 5, 5, 5, 5),
            intArrayOf(5, 5, 5, 5, 5),
            intArrayOf(5, 5, 5, 5, 5),
        )
        val skill = arrayOf(
            intArrayOf(1, 0, 0, 3, 4, 4),
            intArrayOf(1, 2, 0, 2, 3, 2),
            intArrayOf(2, 1, 0, 3, 1, 2),
            intArrayOf(1, 0, 1, 3, 3, 1),
        )

        val actual = Lessons92344().solution(board, skill)

        actual shouldBe 10
    }

    "case_02" {
        val board = arrayOf(
            intArrayOf(1, 2, 3),
            intArrayOf(4, 5, 6),
            intArrayOf(7, 8, 9),
        )
        val skill = arrayOf(
            intArrayOf(1, 1, 1, 2, 2, 4),
            intArrayOf(1, 0, 0, 1, 1, 2),
            intArrayOf(2, 2, 0, 2, 0, 100),
        )

        val actual = Lessons92344().solution(board, skill)

        actual shouldBe 6
    }
})
