package com.keunwon.algorithm.programmers

import io.kotest.core.spec.style.StringSpec
import io.kotest.data.forAll
import io.kotest.data.row
import io.kotest.matchers.shouldBe

internal class Lessons132265Test : StringSpec({
    "case" {
        forAll(
            row(intArrayOf(1, 2, 1, 3, 1, 4, 1, 2), 2),
            row(intArrayOf(1, 2, 3, 1, 4), 0),
        ) { topping, result ->
            val actual = Lessons132265().solution(topping)
            actual shouldBe result
        }
    }
})
