package com.keunwon.algorithm.baekjoon

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

internal class Problem1253Test : StringSpec({
    "case" {
        val arr = intArrayOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
        val actual = Problem1253().solution(arr)
        actual shouldBe 8
    }
})
