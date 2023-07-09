package com.keunwon.algorithm.programmers

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

internal class Lessons135807Test : StringSpec({
    "case_01" {
        val arrayA = intArrayOf(10, 17)
        val arrayB = intArrayOf(5, 20)

        val actual = Lessons135807().solution(arrayA, arrayB)

        actual shouldBe 0
    }

    "case_02" {
        val arrayA = intArrayOf(10, 20)
        val arrayB = intArrayOf(5, 17)

        val actual = Lessons135807().solution(arrayA, arrayB)

        actual shouldBe 10
    }

    "case_03" {
        val arrayA = intArrayOf(14, 35, 119)
        val arrayB = intArrayOf(18, 30, 102)

        val actual = Lessons135807().solution(arrayA, arrayB)

        actual shouldBe 7
    }
})
