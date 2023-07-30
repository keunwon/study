package com.keunwon.algorithm.programmers

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

internal class Lessons84021Test : StringSpec({
    "case_01" {
        val game_board = arrayOf(
            intArrayOf(1, 1, 0, 0, 1, 0),
            intArrayOf(0, 0, 1, 0, 1, 0),
            intArrayOf(0, 1, 1, 0, 0, 1),
            intArrayOf(1, 1, 0, 1, 1, 1),
            intArrayOf(1, 0, 0, 0, 1, 0),
            intArrayOf(0, 1, 1, 1, 0, 0),
        )
        val table = arrayOf(
            intArrayOf(1, 0, 0, 1, 1, 0),
            intArrayOf(1, 0, 1, 0, 1, 0),
            intArrayOf(0, 1, 1, 0, 1, 1),
            intArrayOf(0, 0, 1, 0, 0, 0),
            intArrayOf(1, 1, 0, 1, 1, 0),
            intArrayOf(0, 1, 0, 0, 0, 0),
        )

        val actual = Lessons84021().solution(game_board, table)

        actual shouldBe 14
    }

    "case_02" {
        val game_board = arrayOf(
            intArrayOf(0, 0, 0),
            intArrayOf(1, 1, 0),
            intArrayOf(1, 1, 1),
        )
        val table = arrayOf(
            intArrayOf(1, 1, 1),
            intArrayOf(1, 0, 0),
            intArrayOf(0, 0, 0),
        )

        val actual = Lessons84021().solution(game_board, table)

        actual shouldBe 0
    }
})
