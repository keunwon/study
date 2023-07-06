package com.keunwon.algorithm.programmers

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

internal class Lessons43162Test : StringSpec({
    "case_01" {
        val n = 3
        val computers = arrayOf(
            intArrayOf(1, 1, 0),
            intArrayOf(1, 1, 0),
            intArrayOf(0, 0, 1),
        )

        val actual = Lessons43162().solution(n, computers)

        actual shouldBe 2
    }

    "case_02" {
        val n = 3
        val computers = arrayOf(
            intArrayOf(1, 1, 0),
            intArrayOf(1, 1, 1),
            intArrayOf(0, 1, 1),
        )

        val actual = Lessons43162().solution(n, computers)

        actual shouldBe 1
    }
})
