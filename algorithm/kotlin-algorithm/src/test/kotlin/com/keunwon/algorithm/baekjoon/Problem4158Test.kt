package com.keunwon.algorithm.baekjoon

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

internal class Problem4158Test : StringSpec({
    "case_01" {
        val arr1 = intArrayOf(1, 2, 3)
        val arr2 = intArrayOf(1, 2, 4)

        val actual = Problem4158().solution(arr1, arr2)

        actual shouldBe 2
    }
})
