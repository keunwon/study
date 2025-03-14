package com.keunwon.algorithm.baekjoon

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class Problem1205Test : StringSpec({
    "case-1" {
        val point = 90
        val p = 10
        val numbers = intArrayOf(100, 90, 80)

        val actual = Problem1205().solution(point, p, numbers)

        actual shouldBe 2
    }

    "case-2" {
        val point = 1
        val p = 10
        val numbers = intArrayOf(10, 9, 8, 7, 6, 5, 4, 3, 2, 1)

        val actual = Problem1205().solution(point, p, numbers)

        actual shouldBe -1
    }

    "case-3" {
        val point = 1
        val p = 10
        val numbers = intArrayOf(10, 9, 8, 7, 6, 5, 4, 3, 3, 0)

        val actual = Problem1205().solution(point, p, numbers)

        actual shouldBe 10
    }

    "case-4" {
        val point = 0
        val p = 0
        val numbers = intArrayOf()

        val actual = Problem1205().solution(point, p, numbers)

        actual shouldBe 1
    }
})
