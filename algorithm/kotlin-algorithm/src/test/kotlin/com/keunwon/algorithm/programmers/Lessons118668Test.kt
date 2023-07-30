package com.keunwon.algorithm.programmers

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

internal class Lessons118668Test : StringSpec({
    "case_01" {
        val alp = 10
        val cop = 10
        val problems = arrayOf(
            intArrayOf(10, 15, 2, 1, 2),
            intArrayOf(20, 20, 3, 3, 4),
        )

        val actual = Lessons118668().solution(alp, cop, problems)

        actual shouldBe 15
    }

    "case_02" {
        val alp = 0
        val cop = 0
        val problems = arrayOf(
            intArrayOf(0, 0, 2, 1, 2),
            intArrayOf(4, 5, 3, 1, 2),
            intArrayOf(4, 11, 4, 0, 2),
            intArrayOf(10, 4, 0, 4, 2),
        )

        val actual = Lessons118668().solution(alp, cop, problems)

        actual shouldBe 13
    }
})
