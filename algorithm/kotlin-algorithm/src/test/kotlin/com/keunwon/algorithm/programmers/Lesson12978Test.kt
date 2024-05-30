package com.keunwon.algorithm.programmers

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class Lesson12978Test : StringSpec({
    "case-1" {
        val n = 5
        val road = arrayOf(
            intArrayOf(1, 2, 1),
            intArrayOf(2, 3, 3),
            intArrayOf(5, 2, 2),
            intArrayOf(1, 4, 2),
            intArrayOf(5, 3, 1),
            intArrayOf(5, 4, 2)
        )
        val k = 3

        val actual = Lesson12978().solution(n, road, k)

        actual shouldBe 4
    }

    "case-2" {
        val n = 6
        val road = arrayOf(
            intArrayOf(1, 2, 1),
            intArrayOf(1, 3, 2),
            intArrayOf(2, 3, 2),
            intArrayOf(3, 4, 3),
            intArrayOf(3, 5, 2),
            intArrayOf(3, 5, 3),
            intArrayOf(5, 6, 1)
        )
        val k = 4

        val actual = Lesson12978().solution(n, road, k)

        actual shouldBe 4
    }
})
