package com.keunwon.algorithm.programmers

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

internal class Lessons132266Test : StringSpec({
    "case_01" {
        val n = 3
        val roads = arrayOf(intArrayOf(1, 2), intArrayOf(2, 3))
        val sources = intArrayOf(2, 3)
        val destination = 1

        val actual = Lessons132266().solution(n, roads, sources, destination)

        actual shouldBe intArrayOf(1, 2)
    }

    "case_02" {
        val n = 5
        val roads = arrayOf(
            intArrayOf(1, 2),
            intArrayOf(1, 4),
            intArrayOf(2, 4),
            intArrayOf(2, 5),
            intArrayOf(4, 5),
        )
        val sources = intArrayOf(1, 3, 5)
        val destination = 5

        val actual = Lessons132266().solution(n, roads, sources, destination)

        actual shouldBe intArrayOf(2, -1, 0)
    }
})
