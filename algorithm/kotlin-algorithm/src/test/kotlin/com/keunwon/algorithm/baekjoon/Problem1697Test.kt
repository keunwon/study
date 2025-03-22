package com.keunwon.algorithm.baekjoon

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class Problem1697Test : StringSpec({
    "case-1" {
        val n = 5
        val k = 17

        val actual = Problem1697().solution(n, k)

        actual shouldBe 4
    }
})
