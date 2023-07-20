package com.keunwon.algorithm.baekjoon

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

internal class Problem2631Test : StringSpec({
    "case" {
        val arr = intArrayOf(3, 7, 5, 2, 6, 1, 4)
        val actual = Problem2631().solution(arr)
        actual shouldBe 4
    }
})
