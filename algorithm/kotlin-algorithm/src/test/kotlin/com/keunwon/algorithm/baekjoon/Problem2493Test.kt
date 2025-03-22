package com.keunwon.algorithm.baekjoon

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class Problem2493Test : StringSpec({
    "case-1" {
        val heights = intArrayOf(6, 9, 5, 7, 4)
        val actual = Problem2493().solution(heights)
        actual shouldBe intArrayOf(0, 0, 2, 2, 4)
    }
})
