package com.keunwon.algorithm.baekjoon

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class Problem15684Test : StringSpec({
    "case-1" {
        val n = 2
        val h = 3
        val lines = emptyArray<Pair<Int, Int>>()

        val actual = Problem15684().solution(n, h, lines)

        actual shouldBe 0
    }

    "case-2" {
        val n = 2
        val h = 3
        val lines = arrayOf(1 to 1)

        val actual = Problem15684().solution(n, h, lines)

        actual shouldBe 1
    }

    "case-3" {
        val n = 5
        val h = 6
        val lines = arrayOf(
            1 to 1,
            3 to 2,
            2 to 3,
            5 to 1,
            5 to 4,
        )

        val actual = Problem15684().solution(n, h, lines)

        actual shouldBe 3
    }

    "case-4" {
        val n = 6
        val h = 6
        val lines = arrayOf(
            1 to 1,
            3 to 2,
            1 to 3,
            2 to 5,
            5 to 5,
        )

        val actual = Problem15684().solution(n, h, lines)

        actual shouldBe 3
    }

    "case-5" {
        val n = 5
        val h = 6
        val lines = arrayOf(
            1 to 1,
            2 to 2,
            3 to 3,
            4 to 4,
            3 to 1,
            4 to 2,
            5 to 3,
            6 to 4,
        )

        val actual = Problem15684().solution(n, h, lines)

        actual shouldBe -1
    }

    "case-6" {
        val n = 5
        val h = 6
        val lines = arrayOf(
            1 to 1,
            1 to 3,
            2 to 2,
            2 to 4,
            3 to 1,
            3 to 3,
            4 to 2,
            4 to 4,
            5 to 1,
            5 to 3,
            6 to 2,
            6 to 4,
        )

        val actual = Problem15684().solution(n, h, lines)

        actual shouldBe -1
    }

    "case-7" {
        val n = 5
        val h = 6
        val lines = arrayOf(
            1 to 1,
            3 to 1,
            5 to 2,
            4 to 3,
            2 to 3,
            1 to 4,
        )

        val actual = Problem15684().solution(n, h, lines)

        actual shouldBe 2
    }
})
