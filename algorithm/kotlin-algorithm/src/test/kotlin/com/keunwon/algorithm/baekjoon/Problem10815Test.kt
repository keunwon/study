package com.keunwon.algorithm.baekjoon

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

internal class Problem10815Test : StringSpec({
    "case_01" {
        val arr1 = intArrayOf(6, 3, 2, 10, -10)
        val arr2 = intArrayOf(10, 9, -5, 2, 3, 4, 5, -10)

        val actual = Problem10815().solution(arr1, arr2)

        actual shouldBe intArrayOf(1, 0, 0, 1, 1, 0, 0, 1)
    }
})
