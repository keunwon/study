package com.keunwon.algorithm.baekjoon

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

internal class Problem23971Test : StringSpec({
    "case" {
        val (h, w, n, m) = intArrayOf(5, 4, 1, 1)
        val actual = Problem23971().solution(h, w, n, m)
        actual shouldBe 6
    }
})
