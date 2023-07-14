package com.keunwon.algorithm.baekjoon

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

internal class Problem1205Test : StringSpec({
    "case_01" {
        val target = 90
        val p = 10
        val scores = intArrayOf(100, 90, 80)

        val actual = Problem1205().solution(target, p, scores)

        actual shouldBe 2
    }

    "case_02" {
        val target = 1
        val p = 10
        val scores = intArrayOf(10, 9, 8, 7, 6, 5, 4, 3, 2, 1)

        val actual = Problem1205().solution(target, p, scores)

        actual shouldBe -1
    }

    "case_03" {
        val target = 1
        val p = 10
        val scores = intArrayOf(10, 9, 8, 7, 6, 5, 4, 3, 3, 0)

        val actual = Problem1205().solution(target, p, scores)

        actual shouldBe 10
    }

    "case_0r" {
        val target = 0
        val p = 50
        val scores = intArrayOf()

        val actual = Problem1205().solution(target, p, scores)

        actual shouldBe 1
    }
})
