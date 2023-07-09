package com.keunwon.algorithm.programmers

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

internal class Lessons67259Test : StringSpec({
    "case_01" {
        val board = arrayOf(
            intArrayOf(0, 0, 0),
            intArrayOf(0, 0, 0),
            intArrayOf(0, 0, 0),
        )
        val actual = Lessons67259().solution(board)
        actual shouldBe 900
    }

    "case_02" {
        val board = arrayOf(
            intArrayOf(0, 0, 0, 0, 0, 0, 0, 1),
            intArrayOf(0, 0, 0, 0, 0, 0, 0, 0),
            intArrayOf(0, 0, 0, 0, 0, 1, 0, 0),
            intArrayOf(0, 0, 0, 0, 1, 0, 0, 0),
            intArrayOf(0, 0, 0, 1, 0, 0, 0, 1),
            intArrayOf(0, 0, 1, 0, 0, 0, 1, 0),
            intArrayOf(0, 1, 0, 0, 0, 1, 0, 0),
            intArrayOf(1, 0, 0, 0, 0, 0, 0, 0),
        )
        val actual = Lessons67259().solution(board)
        actual shouldBe 3800
    }

    "case_03" {
        val board = arrayOf(
            intArrayOf(0, 0, 1, 0),
            intArrayOf(0, 0, 0, 0),
            intArrayOf(0, 1, 0, 1),
            intArrayOf(1, 0, 0, 0),
        )
        val actual = Lessons67259().solution(board)
        actual shouldBe 2100
    }

    "case_04" {
        val board = arrayOf(
            intArrayOf(0, 0, 0, 0, 0, 0),
            intArrayOf(0, 1, 1, 1, 1, 0),
            intArrayOf(0, 0, 1, 0, 0, 0),
            intArrayOf(1, 0, 0, 1, 0, 1),
            intArrayOf(0, 1, 0, 0, 0, 1),
            intArrayOf(0, 0, 0, 0, 0, 0),
        )
        val actual = Lessons67259().solution(board)
        actual shouldBe 3200
    }
})
