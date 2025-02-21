package com.keunwon.algorithm.programmers

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class Lesson388352Test : StringSpec({
    "case-1" {
        val n = 10
        val q = arrayOf(
            intArrayOf(1, 2, 3, 4, 5),
            intArrayOf(6, 7, 8, 9, 10),
            intArrayOf(3, 7, 8, 9, 10),
            intArrayOf(2, 5, 7, 9, 10),
            intArrayOf(3, 4, 5, 6, 7),
        )
        val ans = intArrayOf(2, 3, 4, 3, 3)

        val actual = Lesson388352().solution(n, q, ans)

        actual shouldBe 3
    }

    "case-2" {
        val n = 15
        val q = arrayOf(
            intArrayOf(2, 3, 9, 12, 13),
            intArrayOf(1, 4, 6, 7, 9),
            intArrayOf(1, 2, 8, 10, 12),
            intArrayOf(6, 7, 11, 13, 15),
            intArrayOf(1, 4, 10, 11, 14),
        )
        val ans = intArrayOf(2, 1, 3, 0, 1)

        val actual = Lesson388352().solution(n, q, ans)

        actual shouldBe 5
    }
})
