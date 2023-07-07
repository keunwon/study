package com.keunwon.algorithm.programmers

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

internal class Lessons68936Test : StringSpec({
    "case_01" {
        val arr = arrayOf(
            intArrayOf(1, 1, 0, 0),
            intArrayOf(1, 0, 0, 0),
            intArrayOf(1, 0, 0, 1),
            intArrayOf(1, 1, 1, 1),
        )

        val actual = Lessons68936().solution(arr)

        actual shouldBe intArrayOf(4, 9)
    }

    "case_02" {
        val arr = arrayOf(
            intArrayOf(1, 1, 1, 1, 1, 1, 1, 1),
            intArrayOf(0, 1, 1, 1, 1, 1, 1, 1),
            intArrayOf(0, 0, 0, 0, 1, 1, 1, 1),
            intArrayOf(0, 1, 0, 0, 1, 1, 1, 1),
            intArrayOf(0, 0, 0, 0, 0, 0, 1, 1),
            intArrayOf(0, 0, 0, 0, 0, 0, 0, 1),
            intArrayOf(0, 0, 0, 0, 1, 0, 0, 1),
            intArrayOf(0, 0, 0, 0, 1, 1, 1, 1),
        )

        val actual = Lessons68936().solution(arr)

        actual shouldBe intArrayOf(10, 15)
    }
})
