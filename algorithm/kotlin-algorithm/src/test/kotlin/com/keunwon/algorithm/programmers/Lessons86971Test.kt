package com.keunwon.algorithm.programmers

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

internal class Lessons86971Test : StringSpec({
    "case_01" {
        val n = 9
        val wires = arrayOf(
            intArrayOf(1, 3),
            intArrayOf(2, 3),
            intArrayOf(3, 4),
            intArrayOf(4, 5),
            intArrayOf(4, 6),
            intArrayOf(4, 7),
            intArrayOf(7, 8),
            intArrayOf(7, 9),
        )

        val actual = Lessons86971().solution(n, wires)

        actual shouldBe 3
    }

    "case_02" {
        val n = 4
        val wires = arrayOf(
            intArrayOf(1, 2),
            intArrayOf(2, 3),
            intArrayOf(3, 4),
        )

        val actual = Lessons86971().solution(n, wires)

        actual shouldBe 0
    }

    "case_03" {
        val n = 7
        val wires = arrayOf(
            intArrayOf(1, 2),
            intArrayOf(2, 7),
            intArrayOf(3, 7),
            intArrayOf(3, 4),
            intArrayOf(4, 5),
            intArrayOf(6, 7),
        )

        val actual = Lessons86971().solution(n, wires)

        actual shouldBe 1
    }
})
