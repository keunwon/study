package com.keunwon.algorithm.programmers

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class Lesson178870Test : StringSpec({
    "case-1" {
        val sequence = intArrayOf(1, 2, 3, 4, 5)
        val k = 7

        val actual = Lesson178870().solution(sequence, k)

        actual shouldBe intArrayOf(2, 3)
    }

    "case-2" {
        val sequence = intArrayOf(1, 1, 1, 2, 3, 4, 5)
        val k = 5

        val actual = Lesson178870().solution(sequence, k)

        actual shouldBe intArrayOf(6, 6)
    }

    "case-3" {
        val sequence = intArrayOf(2, 2, 2, 2, 2)
        val k = 6

        val actual = Lesson178870().solution(sequence, k)

        actual shouldBe intArrayOf(0, 2)
    }

    "case-4" {
        val sequence = intArrayOf(2, 2, 2, 2, 6)
        val k = 6

        val actual = Lesson178870().solution(sequence, k)

        actual shouldBe intArrayOf(4, 4)
    }
})
