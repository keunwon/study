package com.keunwon.algorithm.baekjoon

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class Problem3190Test : StringSpec({
    "case-1" {
        val n = 6
        val apples = arrayOf(
            intArrayOf(3, 4),
            intArrayOf(2, 5),
            intArrayOf(5, 3),
        )
        val routes = mapOf(
            3 to 'D',
            15 to 'L',
            17 to 'D',
        )

        val actual = Problem3190().solution(n, apples, routes)

        actual shouldBe 9
    }

    "case-2" {
        val n = 10
        val apples = arrayOf(
            intArrayOf(1, 2),
            intArrayOf(1, 3),
            intArrayOf(1, 4),
            intArrayOf(1, 5),
        )
        val routes = hashMapOf(
            8 to 'D',
            10 to 'D',
            11 to 'D',
            13 to 'L',
        )

        val actual = Problem3190().solution(n, apples, routes)

        actual shouldBe 21
    }

    "case-3" {
        val n = 10
        val apples = arrayOf(
            intArrayOf(1, 5),
            intArrayOf(1, 3),
            intArrayOf(1, 2),
            intArrayOf(1, 6),
            intArrayOf(1, 7),
        )
        val routes = hashMapOf(
            8 to 'D',
            10 to 'D',
            11 to 'D',
            13 to 'L',
        )

        val actual = Problem3190().solution(n, apples, routes)

        actual shouldBe 13
    }
})
