package com.keunwon.algorithm.programmers

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

internal class Lessons12950Test : StringSpec({
    "case_01" {
        val arr1 = arrayOf(
            intArrayOf(1, 2),
            intArrayOf(2, 3),
        )
        val arr2 = arrayOf(
            intArrayOf(3, 4),
            intArrayOf(5, 6),
        )

        val actual = Lessons12950().solution(arr1, arr2)

        actual shouldBe arrayOf(
            intArrayOf(4, 6),
            intArrayOf(7, 9),
        )
    }

    "case_02" {
        val arr1 = arrayOf(
            intArrayOf(1),
            intArrayOf(2),
        )
        val arr2 = arrayOf(
            intArrayOf(3),
            intArrayOf(4),
        )

        val actual = Lessons12950().solution(arr1, arr2)

        actual shouldBe arrayOf(
            intArrayOf(4),
            intArrayOf(6),
        )
    }
})
