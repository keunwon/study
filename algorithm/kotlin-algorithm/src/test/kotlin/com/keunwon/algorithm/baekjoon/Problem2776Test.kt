package com.keunwon.algorithm.baekjoon

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

internal class Problem2776Test : StringSpec({
    "case_01" {
        val arr1 = intArrayOf(4, 1, 5, 2, 3)
        val arr2 = intArrayOf(1, 3, 7, 9, 5)

        val actual = Problem2776().solution(arr1, arr2)

        actual shouldBe intArrayOf(1, 1, 0, 0, 1)
    }
})
