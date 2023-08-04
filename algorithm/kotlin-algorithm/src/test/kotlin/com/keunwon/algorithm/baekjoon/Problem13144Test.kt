package com.keunwon.algorithm.baekjoon

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

internal class Problem13144Test : StringSpec({
    "case_01" {
        val arr = intArrayOf(1, 2, 3, 4, 5)
        val actual = Problem13144().solution(arr)
        actual shouldBe 15
    }

    "case_02" {
        val arr = intArrayOf(1, 2, 3, 1, 2)
        val actual = Problem13144().solution(arr)
        actual shouldBe 12
    }

    "case_03" {
        val arr = intArrayOf(1, 1, 1, 1, 1)
        val actual = Problem13144().solution(arr)
        actual shouldBe 5
    }
})
