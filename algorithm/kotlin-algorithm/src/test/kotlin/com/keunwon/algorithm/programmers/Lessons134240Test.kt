package com.keunwon.algorithm.programmers

import io.kotest.core.spec.style.StringSpec
import io.kotest.data.forAll
import io.kotest.data.row
import io.kotest.matchers.shouldBe

internal class Lessons134240Test : StringSpec({
    "case" {
        forAll(
            row(intArrayOf(1, 3, 4, 6), "1223330333221"),
            row(intArrayOf(1, 7, 1, 2), "111303111"),
        ) { food, result ->
            val actual = Lessons134240().solution(food)
            actual shouldBe result
        }
    }
})
