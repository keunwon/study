package com.keunwon.algorithm.baekjoon

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

internal class Problem11659Test : StringSpec({
    "case" {
        val arr = intArrayOf(5, 4, 3, 2, 1)
        val rates = arrayOf(1 to 3, 2 to 4, 5 to 5)

        val actual = Problem11659().solution(arr, rates)

        actual shouldBe intArrayOf(12, 9, 1)
    }
})
