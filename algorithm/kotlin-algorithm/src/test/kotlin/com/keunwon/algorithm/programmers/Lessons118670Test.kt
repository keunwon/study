package com.keunwon.algorithm.programmers

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

internal class Lessons118670Test : StringSpec({
    "case_01" {
        val rc = arrayOf(
            intArrayOf(1, 2, 3),
            intArrayOf(4, 5, 6),
            intArrayOf(7, 8, 9),
        )
        val operations = arrayOf("Rotate", "ShiftRow")

        val actual = Lessons118670().solution(rc, operations)

        actual shouldBe arrayOf(
            intArrayOf(8, 9, 6),
            intArrayOf(4, 1, 2),
            intArrayOf(7, 5, 3),
        )
    }
})
