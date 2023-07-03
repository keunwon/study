package com.keunwon.algorithm.programmers

import io.kotest.core.spec.style.StringSpec
import io.kotest.data.forAll
import io.kotest.data.row
import io.kotest.matchers.shouldBe

internal class Lessons68644Test : StringSpec({
    "case" {
        forAll(
            row(intArrayOf(2, 1, 3, 4, 1), intArrayOf(2, 3, 4, 5, 6, 7)),
            row(intArrayOf(5, 0, 2, 7), intArrayOf(2, 5, 7, 9, 12))
        ) { numbers, answer ->
            val actual = Lessons68644().solution(numbers)
            actual shouldBe answer
        }
    }
})
