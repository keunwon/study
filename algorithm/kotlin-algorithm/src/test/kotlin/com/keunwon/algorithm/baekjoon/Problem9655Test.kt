package com.keunwon.algorithm.baekjoon

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

internal class Problem9655Test : StringSpec({
    "case" {
        val n = 5
        val actual = Problem9655().solution(n)
        actual shouldBe "SK"
    }
})
