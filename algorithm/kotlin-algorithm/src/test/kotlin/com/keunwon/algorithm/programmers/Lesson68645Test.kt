package com.keunwon.algorithm.programmers

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class Lesson68645Test : StringSpec({
    "case-1" {
        val n = 4
        val actual = Lesson68645().solution(n)
        actual shouldBe intArrayOf(1, 2, 9, 3, 10, 8, 4, 5, 6, 7)
    }

    "case-2" {
        val n = 5
        val actual = Lesson68645().solution(n)
        actual shouldBe intArrayOf(1, 2, 12, 3, 13, 11, 4, 14, 15, 10, 5, 6, 7, 8, 9)
    }

    "case-3" {
        val n = 6
        val actual = Lesson68645().solution(n)
        actual shouldBe intArrayOf(1, 2, 15, 3, 16, 14, 4, 17, 21, 13, 5, 18, 19, 20, 12, 6, 7, 8, 9, 10, 11)
    }
})
