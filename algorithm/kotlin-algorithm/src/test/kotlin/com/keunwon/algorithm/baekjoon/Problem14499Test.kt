package com.keunwon.algorithm.baekjoon

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class Problem14499Test : StringSpec({
    "case-1" {
        val map = arrayOf(
            intArrayOf(0, 2),
            intArrayOf(3, 4),
            intArrayOf(5, 6),
            intArrayOf(7, 8),
        )
        val x = 0
        val y = 0
        val dirs = intArrayOf(4, 4, 4, 1, 3, 3, 3, 2)

        val actual = Problem14499().solution(map, x, y, dirs)

        actual shouldBe intArrayOf(0, 0, 3, 0, 0, 8, 6, 3)
    }

    "case-2" {
        val map = arrayOf(
            intArrayOf(1, 2, 3),
            intArrayOf(4, 0, 5),
            intArrayOf(6, 7, 8),
        )
        val x = 1
        val y = 1
        val dirs = intArrayOf(1, 3, 2, 2, 4, 4, 1, 1, 3)

        val actual = Problem14499().solution(map, x, y, dirs)

        actual shouldBe intArrayOf(0, 0, 0, 3, 0, 1, 0, 6, 0)
    }

    "case-3" {
        val map = arrayOf(
            intArrayOf(0, 2),
            intArrayOf(3, 4),
        )
        val x = 0
        val y = 0
        val dirs = intArrayOf(4, 4, 4, 4, 1, 1, 1, 1, 3, 3, 3, 3, 2, 2, 2, 2)
        val actual = Problem14499().solution(map, x, y, dirs)

        actual shouldBe intArrayOf(0, 0, 0, 0)
    }

    "case-4" {
        val map = arrayOf(
            intArrayOf(0, 1, 2),
            intArrayOf(3, 4, 5),
            intArrayOf(6, 7, 8),
        )
        val x = 0
        val y = 0
        val dirs = intArrayOf(4, 4, 1, 1, 3, 3, 2, 2, 4, 4, 1, 1, 3, 3, 2, 2)

        val actual = Problem14499().solution(map, x, y, dirs)

        actual shouldBe intArrayOf(0, 0, 0, 6, 0, 8, 0, 2, 0, 8, 0, 2, 0, 8, 0, 2)
    }
})
