package com.keunwon.algorithm.programmers

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class Lesson132266Test : StringSpec({
    "case-1" {
        val n = 3
        val roads = arrayOf(intArrayOf(1, 2), intArrayOf(2, 3))
        val sources = intArrayOf(2, 3)
        val destination = 1

        val actual = Lesson132266().solution(n, roads, sources, destination)

        actual shouldBe intArrayOf(1, 2)
    }

    "case-2" {
        val n = 5
        val roads = arrayOf(
            intArrayOf(1, 2),
            intArrayOf(1, 4),
            intArrayOf(2, 4),
            intArrayOf(2, 5),
            intArrayOf(4, 5)
        )
        val sources = intArrayOf(1, 3, 5)
        val destination = 5

        val actual = Lesson132266().solution(n, roads, sources, destination)

        actual shouldBe intArrayOf(2, -1, 0)
    }
})