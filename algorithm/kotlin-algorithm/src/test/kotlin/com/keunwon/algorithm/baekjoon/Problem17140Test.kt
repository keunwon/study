package com.keunwon.algorithm.baekjoon

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class Problem17140Test : StringSpec({
    "case-1" {
        val r = 1
        val c = 2
        val k = 2
        val board = arrayOf(
            intArrayOf(1, 2, 1),
            intArrayOf(2, 1, 3),
            intArrayOf(3, 3, 3),
        )

        val actual = Problem17140().solution(r, c, k, board)

        actual shouldBe 0
    }

    "case-2" {
        val r = 1
        val c = 2
        val k = 1
        val board = arrayOf(
            intArrayOf(1, 2, 1),
            intArrayOf(2, 1, 3),
            intArrayOf(3, 3, 3),
        )

        val actual = Problem17140().solution(r, c, k, board)

        actual shouldBe 1
    }

    "case-3" {
        val r = 1
        val c = 2
        val k = 3
        val board = arrayOf(
            intArrayOf(1, 2, 1),
            intArrayOf(2, 1, 3),
            intArrayOf(3, 3, 3),
        )

        val actual = Problem17140().solution(r, c, k, board)

        actual shouldBe 2
    }

    "case-4" {
        val r = 1
        val c = 2
        val k = 4
        val board = arrayOf(
            intArrayOf(1, 2, 1),
            intArrayOf(2, 1, 3),
            intArrayOf(3, 3, 3),
        )

        val actual = Problem17140().solution(r, c, k, board)

        actual shouldBe 52
    }

    "case-5" {
        val r = 1
        val c = 2
        val k = 5
        val board = arrayOf(
            intArrayOf(1, 2, 1),
            intArrayOf(2, 1, 3),
            intArrayOf(3, 3, 3),
        )

        val actual = Problem17140().solution(r, c, k, board)

        actual shouldBe -1
    }

    "case-6" {
        val r = 3
        val c = 3
        val k = 3
        val board = arrayOf(
            intArrayOf(1, 1, 1),
            intArrayOf(1, 1, 1),
            intArrayOf(1, 1, 1),
        )

        val actual = Problem17140().solution(r, c, k, board)

        actual shouldBe 2
    }
})
