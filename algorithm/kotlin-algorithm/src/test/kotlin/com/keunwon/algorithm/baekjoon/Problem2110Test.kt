package com.keunwon.algorithm.baekjoon

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class Problem2110Test : StringSpec({
    "case-1" {
        val c = 3
        val homes = intArrayOf(1, 2, 8, 4, 9)

        val actual = Problem2110().solution(c, homes)

        actual shouldBe 3
    }
})
