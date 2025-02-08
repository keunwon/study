package com.keunwon.algorithm.baekjoon

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class Problem14890Test : StringSpec({
    "case-1" {
        val l = 2
        val board = arrayOf(
            intArrayOf(3, 3, 3, 3, 3, 3),
            intArrayOf(2, 3, 3, 3, 3, 3),
            intArrayOf(2, 2, 2, 3, 2, 3),
            intArrayOf(1, 1, 1, 2, 2, 2),
            intArrayOf(1, 1, 1, 3, 3, 1),
            intArrayOf(1, 1, 2, 3, 3, 2),
        )

        val actual = Problem14890().solution(l, board)

        actual shouldBe 3
    }

    "case-2" {
        val l = 2
        val board = arrayOf(
            intArrayOf(3, 2, 1, 1, 2, 3),
            intArrayOf(3, 2, 2, 1, 2, 3),
            intArrayOf(3, 2, 2, 2, 3, 3),
            intArrayOf(3, 3, 3, 3, 3, 3),
            intArrayOf(3, 3, 3, 3, 2, 2),
            intArrayOf(3, 3, 3, 3, 2, 2),
        )

        val actual = Problem14890().solution(l, board)

        actual shouldBe 7
    }

    "case-3" {
        val l = 3
        val board = arrayOf(
            intArrayOf(3, 2, 1, 1, 2, 3),
            intArrayOf(3, 2, 2, 1, 2, 3),
            intArrayOf(3, 2, 2, 2, 3, 3),
            intArrayOf(3, 3, 3, 3, 3, 3),
            intArrayOf(3, 3, 3, 3, 2, 2),
            intArrayOf(3, 3, 3, 3, 2, 2),
        )

        val actual = Problem14890().solution(l, board)

        actual shouldBe 3
    }

    "case-4" {
        val l = 1
        val board = arrayOf(
            intArrayOf(3, 2, 1, 1, 2, 3),
            intArrayOf(3, 2, 2, 1, 2, 3),
            intArrayOf(3, 2, 2, 2, 3, 3),
            intArrayOf(3, 3, 3, 3, 3, 3),
            intArrayOf(3, 3, 3, 3, 2, 2),
            intArrayOf(3, 3, 3, 3, 2, 2),
        )

        val actual = Problem14890().solution(l, board)

        actual shouldBe 11
    }
})
