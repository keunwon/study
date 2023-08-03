package com.keunwon.algorithm.baekjoon

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

internal class Problem20055Test : StringSpec({
    "case_01" {
        val n = 3
        val k = 2
        val belt = intArrayOf(1, 2, 1, 2, 1, 2)

        val actual = Problem20055().solution(n, k, belt)

        actual shouldBe 2
    }

    "case_02" {
        val n = 3
        val k = 6
        val belt = intArrayOf(10, 10, 10, 10, 10, 10)

        val actual = Problem20055().solution(n, k, belt)

        actual shouldBe 31
    }

    "case_03" {
        val n = 4
        val k = 5
        val belt = intArrayOf(10, 1, 10, 6, 3, 4, 8, 2)

        val actual = Problem20055().solution(n, k, belt)

        actual shouldBe 24
    }

    "case_04" {
        val n = 5
        val k = 8
        val belt = intArrayOf(100, 99, 60, 80, 30, 20, 10, 89, 99, 100)

        val actual = Problem20055().solution(n, k, belt)

        actual shouldBe 472
    }
})
