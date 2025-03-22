package com.keunwon.algorithm.baekjoon

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class Problem20922Test : StringSpec({
    "case-1" {
        val k = 2
        val numbers = intArrayOf(3, 2, 5, 5, 6, 4, 4, 5, 7)

        val actual = Problem20922().solution(k, numbers)

        actual shouldBe 7
    }

    "case-2" {
        val k = 1
        val numbers = intArrayOf(1, 2, 3, 4, 5, 6, 6, 7, 8, 9)

        val actual = Problem20922().solution(k, numbers)

        actual shouldBe 6
    }
})
