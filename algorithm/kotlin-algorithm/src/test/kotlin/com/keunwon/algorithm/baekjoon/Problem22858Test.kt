package com.keunwon.algorithm.baekjoon

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

internal class Problem22858Test : StringSpec({
    "case_01" {
        val k = 2
        val card = intArrayOf(0, 4, 1, 3, 5, 2)
        val shuffleOrder = intArrayOf(0, 4, 3, 1, 2, 5)

        val actual = Problem22858().solution(k, card, shuffleOrder)

        actual shouldBe intArrayOf(1, 4, 5, 3, 2)
    }

    "case_02" {
        val k = 1
        val card = intArrayOf(0, 4, 3, 2, 1)
        val shuffleOrder = intArrayOf(0, 4, 3, 2, 1)

        val actual = Problem22858().solution(k, card, shuffleOrder)

        actual shouldBe intArrayOf(1, 2, 3, 4)
    }
})
