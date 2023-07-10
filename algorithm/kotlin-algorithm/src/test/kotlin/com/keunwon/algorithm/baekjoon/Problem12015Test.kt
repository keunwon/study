package com.keunwon.algorithm.baekjoon

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

internal class Problem12015Test : StringSpec({
    "case_01" {
        val arr = intArrayOf(10, 20, 10, 30, 20, 50)
        val actual = Problem12015().solution(arr)
        actual shouldBe 4
    }
})
