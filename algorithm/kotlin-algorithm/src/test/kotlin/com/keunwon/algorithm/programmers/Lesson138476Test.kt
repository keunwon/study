package com.keunwon.algorithm.programmers

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class Lesson138476Test : StringSpec({
    "case-1" {
        val k = 6
        val tangerine = intArrayOf(1, 3, 2, 5, 4, 5, 2, 3)

        val actual = Lesson138476().solution(k, tangerine)

        actual shouldBe 3
    }

    "case-2" {
        val k = 4
        val tangerine = intArrayOf(1, 3, 2, 5, 4, 5, 2, 3)

        val actual = Lesson138476().solution(k, tangerine)

        actual shouldBe 2
    }

    "case-3" {
        val k = 2
        val tangerine = intArrayOf(1, 1, 1, 1, 2, 2, 2, 3)

        val actual = Lesson138476().solution(k, tangerine)

        actual shouldBe 1
    }

    "case-4" {
        val k = 4
        val tangerine = intArrayOf(1, 2, 3, 4)

        val actual = Lesson138476().solution(k, tangerine)

        actual shouldBe tangerine.size
    }

    "case-5" {
        val k = 4
        val tangerine = intArrayOf(1, 1, 1, 4)

        val actual = Lesson138476().solution(k, tangerine)

        actual shouldBe 2
    }
})
