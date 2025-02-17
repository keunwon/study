package com.keunwon.algorithm.baekjoon

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class Problem17822Test : StringSpec({
    "case-1" {
        val points = arrayOf(
            intArrayOf(1, 1, 2, 3),
            intArrayOf(5, 2, 4, 2),
            intArrayOf(3, 1, 3, 5),
            intArrayOf(2, 1, 3, 2),
        )
        val commands = arrayOf(
            intArrayOf(2, 0, 1),
        )

        val actual = Problem17822().solution(points, commands)

        actual shouldBe 30
    }

    "case-2" {
        val points = arrayOf(
            intArrayOf(1, 1, 2, 3),
            intArrayOf(5, 2, 4, 2),
            intArrayOf(3, 1, 3, 5),
            intArrayOf(2, 1, 3, 2),
        )
        val commands = arrayOf(
            intArrayOf(2, 0, 1),
            intArrayOf(3, 1, 3),
        )

        val actual = Problem17822().solution(points, commands)

        actual shouldBe 22
    }

    "case-3" {
        val points = arrayOf(
            intArrayOf(1, 1, 2, 3),
            intArrayOf(5, 2, 4, 2),
            intArrayOf(3, 1, 3, 5),
            intArrayOf(2, 1, 3, 2),
        )
        val commands = arrayOf(
            intArrayOf(2, 0, 1),
            intArrayOf(3, 1, 3),
            intArrayOf(2, 0, 2),
        )

        val actual = Problem17822().solution(points, commands)

        actual shouldBe 22
    }

    "case-4" {
        val points = arrayOf(
            intArrayOf(1, 1, 2, 3),
            intArrayOf(5, 2, 4, 2),
            intArrayOf(3, 1, 3, 5),
            intArrayOf(2, 1, 3, 2),
        )
        val commands = arrayOf(
            intArrayOf(2, 0, 1),
            intArrayOf(3, 1, 3),
            intArrayOf(2, 0, 2),
            intArrayOf(3, 1, 1),
        )

        val actual = Problem17822().solution(points, commands)

        actual shouldBe 0
    }

    "case-5" {
        val points = arrayOf(
            intArrayOf(1, 2, 3, 4, 5, 6),
            intArrayOf(2, 3, 4, 5, 6, 7),
            intArrayOf(3, 4, 5, 6, 7, 8),
            intArrayOf(4, 5, 6, 7, 8, 9),
        )
        val commands = arrayOf(
            intArrayOf(2, 1, 4),
            intArrayOf(3, 0, 1),
            intArrayOf(2, 1, 2),
        )

        val actual = Problem17822().solution(points, commands)

        actual shouldBe 26
    }
})
