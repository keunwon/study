package com.keunwon.algorithm.baekjoon

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

internal class Problem2292Test : StringSpec({
    "case" {
        val n = 13
        val actual = Problem2292().solution(n)
        actual shouldBe 3
    }
})
