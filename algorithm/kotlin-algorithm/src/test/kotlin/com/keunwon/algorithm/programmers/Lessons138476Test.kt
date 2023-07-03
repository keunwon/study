package com.keunwon.algorithm.programmers

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

internal class Lessons138476Test : StringSpec({
    "case_01" {
        val k = 6
        val tangerine = intArrayOf(1, 3, 2, 5, 4, 5, 2, 3)

        val actual = Lessons138476().solution(k, tangerine)

        actual shouldBe 3
    }

    "case_02" {
        val k = 4
        val tangerine = intArrayOf(1, 3, 2, 5, 4, 5, 2, 3)

        val actual = Lessons138476().solution(k, tangerine)

        actual shouldBe 2
    }

    "case_03" {
        val k = 2
        val tangerine = intArrayOf(1, 1, 1, 1, 2, 2, 2, 3)

        val actual = Lessons138476().solution(k, tangerine)

        actual shouldBe 1
    }
})
