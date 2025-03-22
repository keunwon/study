package com.keunwon.algorithm.baekjoon

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class Problem2531Test : StringSpec({
    "case-1" {
        val d = 30
        val k = 4
        val c = 30
        val types = intArrayOf(7, 9, 7, 30, 2, 7, 9, 25)

        val actual = Problem2531().solution(d, k, c, types)

        actual shouldBe 5
    }

    "case-2" {
        val d = 50
        val k = 4
        val c = 7
        val types = intArrayOf(2, 7, 9, 25, 7, 9, 7, 30)

        val actual = Problem2531().solution(d, k, c, types)

        actual shouldBe 4
    }
})
