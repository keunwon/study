package com.keunwon.algorithm.programmers

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

internal class Lessons42889Test : StringSpec({
    "case_01" {
        val n = 5
        val stages = intArrayOf(2, 1, 2, 6, 2, 4, 3, 3)

        val actual = Lessons42889().solution(n, stages)

        actual shouldBe intArrayOf(3, 4, 2, 1, 5)
    }

    "case_02" {
        val n = 4
        val stages = intArrayOf(4, 4, 4, 4, 4)

        val actual = Lessons42889().solution(n, stages)

        actual shouldBe intArrayOf(4, 1, 2, 3)
    }

    "case_03" {
        val n = 2
        val stages = intArrayOf(1, 1, 1, 1)

        val actual = Lessons42889().solution(n, stages)

        actual shouldBe intArrayOf(1, 2)
    }
})
