package com.keunwon.algorithm.againresolve

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

internal class ALessons12949Test : StringSpec({
    "case_01" {
        val arr1 = arrayOf(
            intArrayOf(1, 4),
            intArrayOf(3, 2),
            intArrayOf(4, 1),
        )
        val arr2 = arrayOf(
            intArrayOf(3, 3),
            intArrayOf(3, 3),
        )

        val actual = ALessons12949().solution(arr1, arr2)

        actual shouldBe arrayOf(
            intArrayOf(15, 15),
            intArrayOf(15, 15),
            intArrayOf(15, 15),
        )
    }

    "case_02" {
        val arr1 = arrayOf(
            intArrayOf(2, 3, 2),
            intArrayOf(4, 2, 4),
            intArrayOf(3, 1, 4),
        )
        val arr2 = arrayOf(
            intArrayOf(5, 4, 3),
            intArrayOf(2, 4, 1),
            intArrayOf(3, 1, 1),
        )

        val actual = ALessons12949().solution(arr1, arr2)

        actual shouldBe arrayOf(
            intArrayOf(22, 22, 11),
            intArrayOf(36, 28, 18),
            intArrayOf(29, 20, 14),
        )
    }
})
