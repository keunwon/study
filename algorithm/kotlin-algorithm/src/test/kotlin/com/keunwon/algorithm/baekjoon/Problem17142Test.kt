package com.keunwon.algorithm.baekjoon

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class Problem17142Test : StringSpec({
    "case-1" {
        val m = 3
        val board = arrayOf(
            intArrayOf(2, 0, 0, 0, 1, 1, 0),
            intArrayOf(0, 0, 1, 0, 1, 2, 0),
            intArrayOf(0, 1, 1, 0, 1, 0, 0),
            intArrayOf(0, 1, 0, 0, 0, 0, 0),
            intArrayOf(0, 0, 0, 2, 0, 1, 1),
            intArrayOf(0, 1, 0, 0, 0, 0, 0),
            intArrayOf(2, 1, 0, 0, 0, 0, 2),
        )

        val actual = Problem17142().solution(m, board)

        actual shouldBe 4
    }

    "case-2" {
        val m = 3
        val board = arrayOf(
            intArrayOf(2, 0, 2, 0, 1, 1, 0),
            intArrayOf(0, 0, 1, 0, 1, 2, 0),
            intArrayOf(0, 1, 1, 2, 1, 0, 0),
            intArrayOf(2, 1, 0, 0, 0, 0, 2),
            intArrayOf(0, 0, 0, 2, 0, 1, 1),
            intArrayOf(0, 1, 0, 0, 0, 0, 0),
            intArrayOf(2, 1, 0, 0, 2, 0, 2),
        )

        val actual = Problem17142().solution(m, board)

        actual shouldBe 4
    }

    "case-3" {
        val m = 4
        val board = arrayOf(
            intArrayOf(2, 0, 2, 0, 1, 1, 0),
            intArrayOf(0, 0, 1, 0, 1, 2, 0),
            intArrayOf(0, 1, 1, 2, 1, 0, 0),
            intArrayOf(2, 1, 0, 0, 0, 0, 2),
            intArrayOf(0, 0, 0, 2, 0, 1, 1),
            intArrayOf(0, 1, 0, 0, 0, 0, 0),
            intArrayOf(2, 1, 0, 0, 2, 0, 2),
        )

        val actual = Problem17142().solution(m, board)

        actual shouldBe 4
    }

    "case-4" {
        val m = 5
        val board = arrayOf(
            intArrayOf(2, 0, 2, 0, 1, 1, 0),
            intArrayOf(0, 0, 1, 0, 1, 2, 0),
            intArrayOf(0, 1, 1, 2, 1, 0, 0),
            intArrayOf(2, 1, 0, 0, 0, 0, 2),
            intArrayOf(0, 0, 0, 2, 0, 1, 1),
            intArrayOf(0, 1, 0, 0, 0, 0, 0),
            intArrayOf(2, 1, 0, 0, 2, 0, 2),
        )

        val actual = Problem17142().solution(m, board)

        actual shouldBe 3
    }

    "case-5" {
        val m = 3
        val board = arrayOf(
            intArrayOf(2, 0, 2, 0, 1, 1, 0),
            intArrayOf(0, 0, 1, 0, 1, 0, 0),
            intArrayOf(0, 1, 1, 1, 1, 0, 0),
            intArrayOf(2, 1, 0, 0, 0, 0, 2),
            intArrayOf(1, 0, 0, 0, 0, 1, 1),
            intArrayOf(0, 1, 0, 0, 0, 0, 0),
            intArrayOf(2, 1, 0, 0, 2, 0, 2),
        )

        val actual = Problem17142().solution(m, board)

        actual shouldBe 7
    }

    "case-6" {
        val m = 2
        val board = arrayOf(
            intArrayOf(2, 0, 2, 0, 1, 1, 0),
            intArrayOf(0, 0, 1, 0, 1, 0, 0),
            intArrayOf(0, 1, 1, 1, 1, 0, 0),
            intArrayOf(2, 1, 0, 0, 0, 0, 2),
            intArrayOf(1, 0, 0, 0, 0, 1, 1),
            intArrayOf(0, 1, 0, 0, 0, 0, 0),
            intArrayOf(2, 1, 0, 0, 2, 0, 2),
        )

        val actual = Problem17142().solution(m, board)

        actual shouldBe -1
    }

    "case-7" {
        val m = 1
        val board = arrayOf(
            intArrayOf(2, 2, 2, 1, 1),
            intArrayOf(2, 1, 1, 1, 1),
            intArrayOf(2, 1, 1, 1, 1),
            intArrayOf(2, 1, 1, 1, 1),
            intArrayOf(2, 2, 2, 1, 1),
        )

        val actual = Problem17142().solution(m, board)

        actual shouldBe 0
    }

    "case-8" {
        val m = 2
        val board = arrayOf(
            intArrayOf(0, 0, 0, 2, 2),
            intArrayOf(1, 1, 1, 1, 0),
            intArrayOf(2, 1, 2, 1, 0),
            intArrayOf(2, 1, 1, 1, 1),
            intArrayOf(2, 2, 2, 2, 0),
        )

        val actual = Problem17142().solution(m, board)

        actual shouldBe 3
    }

    "case-9" {
        val m = 1
        val board = arrayOf(
            intArrayOf(1, 1, 1, 1),
            intArrayOf(1, 1, 1, 1),
            intArrayOf(1, 1, 1, 1),
            intArrayOf(0, 2, 2, 0),
        )

        val actual = Problem17142().solution(m, board)

        actual shouldBe 2
    }
})
