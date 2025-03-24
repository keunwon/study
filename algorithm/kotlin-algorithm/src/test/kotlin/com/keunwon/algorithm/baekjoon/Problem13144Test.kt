package com.keunwon.algorithm.baekjoon

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class Problem13144Test : StringSpec({
    "case-1" {
        val numbers = intArrayOf(1, 2, 3, 4, 5)
        val actual = Problem13144().solution(numbers)
        actual shouldBe 15
    }

    "case-2" {
        val numbers = intArrayOf(1, 2, 3, 1, 2)
        val actual = Problem13144().solution(numbers)
        actual shouldBe 12
    }

    "case-3" {
        val numbers = intArrayOf(1, 1, 1, 1, 1)
        val actual = Problem13144().solution(numbers)
        actual shouldBe 5
    }
})
