package com.keunwon.algorithm.programmers

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class Lesson67256Test : StringSpec({
    "case-1" {
        val numbers = intArrayOf(1, 3, 4, 5, 8, 2, 1, 4, 5, 9, 5)
        val hand = "right"

        val actual = Lesson67256().solution(numbers, hand)

        actual shouldBe "LRLLLRLLRRL"
    }

    "case-2" {
        val numbers = intArrayOf(7, 0, 8, 2, 8, 3, 1, 5, 7, 6, 2)
        val hand = "left"

        val actual = Lesson67256().solution(numbers, hand)

        actual shouldBe "LRLLRRLLLRR"
    }

    "case-3" {
        val numbers = intArrayOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 0)
        val hand = "right"

        val actual = Lesson67256().solution(numbers, hand)

        actual shouldBe "LLRLLRLLRL"
    }
})
