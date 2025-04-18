package com.keunwon.algorithm.programmers

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class Lesson60063Test : StringSpec({
    "case-1" {
        val board = arrayOf(
            intArrayOf(0, 0, 0, 1, 1),
            intArrayOf(0, 0, 0, 1, 0),
            intArrayOf(0, 1, 0, 1, 1),
            intArrayOf(1, 1, 0, 0, 1),
            intArrayOf(0, 0, 0, 0, 0),
        )

        val actual = Lesson60063().solution(board)

        actual shouldBe 7
    }
})
