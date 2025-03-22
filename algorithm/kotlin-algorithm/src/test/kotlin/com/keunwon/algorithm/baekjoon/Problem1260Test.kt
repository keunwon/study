package com.keunwon.algorithm.baekjoon

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class Problem1260Test : StringSpec({
    "case-1" {
        val n = 4
        val v = 1
        val edges = arrayOf(
            1 to 2,
            1 to 3,
            1 to 4,
            2 to 4,
            3 to 4,
        )

        val actual = Problem1260().solution(n, v, edges)

        actual shouldBe arrayOf(
            intArrayOf(1, 2, 4, 3),
            intArrayOf(1, 2, 3, 4),
        )
    }

    "case-2" {
        val n = 5
        val v = 3
        val edges = arrayOf(
            5 to 4,
            5 to 2,
            1 to 2,
            3 to 4,
            3 to 1,
        )

        val actual = Problem1260().solution(n, v, edges)

        actual shouldBe arrayOf(
            intArrayOf(3, 1, 2, 5, 4),
            intArrayOf(3, 1, 4, 2, 5),
        )
    }

    "case-3" {
        val n = 1000
        val v = 1000
        val edges = arrayOf(999 to 1000)

        val actual = Problem1260().solution(n, v, edges)

        actual shouldBe arrayOf(
            intArrayOf(1000, 999),
            intArrayOf(1000, 999),
        )
    }

})
