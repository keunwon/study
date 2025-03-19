package com.keunwon.algorithm.baekjoon

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class Problem2512Test : StringSpec({
    "case-1" {
        val areas = intArrayOf(120, 110, 140, 150)
        val m = 485

        val actual = Problem2512().solution(areas, m)

        actual shouldBe 127
    }

    "case-2" {
        val areas = intArrayOf(70, 80, 30, 40, 100)
        val m = 450

        val actual = Problem2512().solution(areas, m)

        actual shouldBe 100
    }
})
