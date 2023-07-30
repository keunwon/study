package com.keunwon.algorithm.programmers

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

internal class Lessons133500Test : StringSpec({
    "case_01" {
        val n = 8
        val lighthouse = arrayOf(
            intArrayOf(1, 2),
            intArrayOf(1, 3),
            intArrayOf(1, 4),
            intArrayOf(1, 5),
            intArrayOf(5, 6),
            intArrayOf(5, 7),
            intArrayOf(5, 8),
        )

        val actual = Lessons133500().solution(n, lighthouse)

        actual shouldBe 2
    }

    "case_02" {
        val n = 10
        val lighthouse = arrayOf(
            intArrayOf(4, 1),
            intArrayOf(5, 1),
            intArrayOf(5, 6),
            intArrayOf(7, 6),
            intArrayOf(1, 2),
            intArrayOf(1, 3),
            intArrayOf(6, 8),
            intArrayOf(2, 9),
            intArrayOf(9, 10),
        )

        val actual = Lessons133500().solution(n, lighthouse)

        actual shouldBe 3
    }
})
