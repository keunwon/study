package com.keunwon.algorithm.programmers

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

internal class Lessons12910Test : StringSpec({
    "case_01" {
        val arr = intArrayOf(5, 9, 7, 10)
        val divisor = 5

        val actual = Lessons12910().solution(arr, divisor)

        actual shouldBe intArrayOf(5, 10)
    }

    "case_02" {
        val arr = intArrayOf(2, 36, 1, 3)
        val divisor = 1

        val actual = Lessons12910().solution(arr, divisor)

        actual shouldBe intArrayOf(1, 2, 3, 36)
    }

    "case_03" {
        val arr = intArrayOf(3, 2, 6)
        val divisor = 10

        val actual = Lessons12910().solution(arr, divisor)

        actual shouldBe intArrayOf(-1)
    }
})
