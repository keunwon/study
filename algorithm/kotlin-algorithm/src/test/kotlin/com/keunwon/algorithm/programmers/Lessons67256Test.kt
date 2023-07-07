package com.keunwon.algorithm.programmers

import io.kotest.core.spec.style.StringSpec
import io.kotest.data.forAll
import io.kotest.data.row
import io.kotest.matchers.shouldBe

internal class Lessons67256Test : StringSpec({
    "case" {
        forAll(
            row(intArrayOf(1, 3, 4, 5, 8, 2, 1, 4, 5, 9, 5), "right", "LRLLLRLLRRL"),
            row(intArrayOf(7, 0, 8, 2, 8, 3, 1, 5, 7, 6, 2), "left", "LRLLRRLLLRR"),
            row(intArrayOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 0), "right", "LLRLLRLLRL"),
        ) { numbers, hand, result ->
            val actual = Lessons67256().solution(numbers, hand)
            actual shouldBe result
        }
    }
})
