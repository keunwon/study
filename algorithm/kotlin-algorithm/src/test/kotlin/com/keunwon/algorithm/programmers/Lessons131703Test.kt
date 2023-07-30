package com.keunwon.algorithm.programmers

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

internal class Lessons131703Test : StringSpec({
    "case_01" {
        val beginning = arrayOf(
            intArrayOf(0, 1, 0, 0, 0),
            intArrayOf(1, 0, 1, 0, 1),
            intArrayOf(0, 1, 1, 1, 0),
            intArrayOf(1, 0, 1, 1, 0),
            intArrayOf(0, 1, 0, 1, 0),
        )
        val target = arrayOf(
            intArrayOf(0, 0, 0, 1, 1),
            intArrayOf(0, 0, 0, 0, 1),
            intArrayOf(0, 0, 1, 0, 1),
            intArrayOf(0, 0, 0, 1, 0),
            intArrayOf(0, 0, 0, 0, 1),
        )

        val actual = Lessons131703().solution(beginning, target)

        actual shouldBe 5
    }

    "case_02" {
        val begging = arrayOf(
            intArrayOf(0, 0, 0),
            intArrayOf(0, 0, 0),
            intArrayOf(0, 0, 0),
        )
        val target = arrayOf(
            intArrayOf(1, 0, 1),
            intArrayOf(0, 0, 0),
            intArrayOf(0, 0, 0),
        )

        val actual = Lessons131703().solution(begging, target)

        actual shouldBe -1
    }
})
