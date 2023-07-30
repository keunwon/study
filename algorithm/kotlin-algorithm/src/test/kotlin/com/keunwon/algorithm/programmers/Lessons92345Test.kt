package com.keunwon.algorithm.programmers

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

internal class Lessons92345Test : StringSpec({
    "case_01" {
        val board = arrayOf(
            intArrayOf(1, 1, 1),
            intArrayOf(1, 1, 1),
            intArrayOf(1, 1, 1),
        )
        val aloc = intArrayOf(1, 0)
        val bloc = intArrayOf(1, 2)

        val actual = Lessons92345().solution(board, aloc, bloc)

        actual shouldBe 5
    }

    "case_02" {
        val board = arrayOf(
            intArrayOf(1, 1, 1),
            intArrayOf(1, 0, 1),
            intArrayOf(1, 1, 1),
        )
        val aloc = intArrayOf(1, 0)
        val bloc = intArrayOf(1, 2)

        val actual = Lessons92345().solution(board, aloc, bloc)

        actual shouldBe 4
    }

    "case_03" {
        val board = arrayOf(
            intArrayOf(1, 1, 1, 1, 1)
        )
        val aloc = intArrayOf(0, 0)
        val bloc = intArrayOf(0, 4)

        val actual = Lessons92345().solution(board, aloc, bloc)

        actual shouldBe 4
    }

    "case_04" {
        val board = arrayOf(
            intArrayOf(1),
        )
        val aloc = intArrayOf(0, 0)
        val bloc = intArrayOf(0, 0)

        val actual = Lessons92345().solution(board, aloc, bloc)

        actual shouldBe 0
    }
})
