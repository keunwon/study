package com.keunwon.algorithm.baekjoon

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

internal class Problem17780Test : StringSpec({
    "case_01" {
        val map = arrayOf(
            intArrayOf(0, 0, 2, 0),
            intArrayOf(0, 0, 1, 0),
            intArrayOf(0, 0, 1, 2),
            intArrayOf(0, 2, 0, 0),
        )
        val targets = arrayOf(
            Triple(2, 1, 1),
            Triple(3, 2, 3),
            Triple(2, 2, 1),
            Triple(4, 1, 2),
        )

        val actual = Problem17780().solution(map, targets)

        actual shouldBe -1
    }

    "case_02" {
        val map = arrayOf(
            intArrayOf(0, 0, 0, 0),
            intArrayOf(0, 0, 0, 0),
            intArrayOf(0, 0, 0, 0),
            intArrayOf(0, 0, 0, 0),
        )
        val targets = arrayOf(
            Triple(1, 1, 1),
            Triple(1, 2, 1),
            Triple(1, 3, 1),
            Triple(1, 4, 1),
        )

        val actual = Problem17780().solution(map, targets)

        actual shouldBe 1
    }

    "case_03" {
        val map = arrayOf(
            intArrayOf(0, 0, 0, 0),
            intArrayOf(0, 0, 0, 0),
            intArrayOf(0, 0, 0, 0),
            intArrayOf(0, 0, 0, 0),
        )
        val targets = arrayOf(
            Triple(1, 1, 1),
            Triple(1, 2, 1),
            Triple(1, 3, 1),
            Triple(2, 4, 3),
        )

        val actual = Problem17780().solution(map, targets)

        actual shouldBe 1
    }

    "case_04" {
        val map = arrayOf(
            intArrayOf(0, 0, 0, 0),
            intArrayOf(0, 0, 0, 0),
            intArrayOf(0, 0, 0, 0),
            intArrayOf(0, 0, 0, 0),
        )
        val targets = arrayOf(
            Triple(1, 1, 1),
            Triple(1, 2, 1),
            Triple(1, 3, 1),
            Triple(3, 3, 3),
        )

        val actual = Problem17780().solution(map, targets)

        actual shouldBe 2
    }

    "case_05" {
        val map = arrayOf(
            intArrayOf(0, 0, 2, 0),
            intArrayOf(0, 0, 1, 0),
            intArrayOf(0, 0, 1, 2),
            intArrayOf(0, 2, 0, 0),
        )
        val targets = arrayOf(
            Triple(2, 1, 1),
            Triple(3, 2, 3),
            Triple(2, 2, 1),
            Triple(4, 1, 3),
        )

        val actual = Problem17780().solution(map, targets)

        actual shouldBe 8
    }

    "case_06" {
        val map = arrayOf(
            intArrayOf(0, 1, 2, 0, 1, 1),
            intArrayOf(1, 2, 0, 1, 1, 0),
            intArrayOf(2, 1, 0, 1, 1, 0),
            intArrayOf(1, 0, 1, 1, 0, 2),
            intArrayOf(2, 0, 1, 2, 0, 1),
            intArrayOf(0, 2, 1, 0, 2, 1),
        )
        val targets = arrayOf(
            Triple(1, 1, 1),
            Triple(2, 2, 2),
            Triple(3, 3, 4),
            Triple(4, 4, 1),
            Triple(5, 5, 3),
            Triple(6, 6, 2),
            Triple(1, 6, 3),
            Triple(6, 1, 2),
            Triple(2, 4, 3),
            Triple(4, 2, 1),
        )

        val actual = Problem17780().solution(map, targets)

        actual shouldBe 9
    }
})
