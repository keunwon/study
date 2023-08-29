package com.keunwon.algorithm.baekjoon

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

internal class Problem16932Test : StringSpec({
    "case_01" {
        val map = arrayOf(
            intArrayOf(0, 1, 1),
            intArrayOf(0, 0, 1),
            intArrayOf(0, 1, 0),
        )

        val actual = Problem16932().solution(map)

        actual shouldBe 5
    }

    "case_02" {
        val map = arrayOf(
            intArrayOf(1, 1, 0, 0),
            intArrayOf(1, 0, 1, 0),
            intArrayOf(1, 0, 1, 0),
            intArrayOf(0, 1, 1, 0),
            intArrayOf(1, 0, 0, 1),
        )

        val actual = Problem16932().solution(map)

        actual shouldBe 10
    }

    "case_03" {
        val map = arrayOf(
            intArrayOf(0, 1, 0, 1),
            intArrayOf(0, 0, 0, 1),
            intArrayOf(1, 1, 0, 1),
        )

        val actual = Problem16932().solution(map)

        actual shouldBe 6
    }
})
