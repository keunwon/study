package com.keunwon.algorithm.programmers

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

internal class Lessons64061Test : StringSpec({
    "case" {
        val board = arrayOf(
            intArrayOf(0, 0, 0, 0, 0),
            intArrayOf(0, 0, 1, 0, 3),
            intArrayOf(0, 2, 5, 0, 1),
            intArrayOf(4, 2, 4, 4, 2),
            intArrayOf(3, 5, 1, 3, 1),
        )
        val moves = intArrayOf(1, 5, 3, 5, 1, 2, 1, 4)

        val actual = Lessons64061().solution(board, moves)

        actual shouldBe 4
    }
})
