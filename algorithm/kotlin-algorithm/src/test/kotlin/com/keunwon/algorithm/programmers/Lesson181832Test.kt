package com.keunwon.algorithm.programmers

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class Lesson181832Test : StringSpec({
    "case-1" {
        val n = 4
        val actual = Lesson181832().solution(n)

        actual shouldBe arrayOf(
            intArrayOf(1, 2, 3, 4),
            intArrayOf(12, 13, 14, 5),
            intArrayOf(11, 16, 15, 6),
            intArrayOf(10, 9, 8, 7),
        )
    }

    "case-2" {
        val n = 5
        val actual = Lesson181832().solution(n)

        actual shouldBe arrayOf(
            intArrayOf(1, 2, 3, 4, 5),
            intArrayOf(16, 17, 18, 19, 6),
            intArrayOf(15, 24, 25, 20, 7),
            intArrayOf(14, 23, 22, 21, 8),
            intArrayOf(13, 12, 11, 10, 9),
        )
    }
})
