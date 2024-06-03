package com.keunwon.algorithm.programmers

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class Lesson43236Test : StringSpec({
    "case-1" {
        val distance = 25
        val rocks = intArrayOf(2, 14, 11, 21, 17)
        val n = 2

        val actual = Lesson43236().solution(distance, rocks, n)

        actual shouldBe 4
    }
})
