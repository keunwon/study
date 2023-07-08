package com.keunwon.algorithm.programmers

import io.kotest.core.spec.style.StringSpec
import io.kotest.data.blocking.forAll
import io.kotest.data.row
import io.kotest.matchers.shouldBe

internal class Lessons133502Test : StringSpec({
    "case" {
        forAll(
            row(intArrayOf(2, 1, 1, 2, 3, 1, 2, 3, 1), 2),
            row(intArrayOf(1, 3, 2, 1, 2, 1, 3, 1, 2), 0),
            row(intArrayOf(2, 1, 2, 3, 1, 2, 3, 1, 1), 1),
        ) { ingredient, result ->
            val actual = Lessons133502().solution(ingredient)
            actual shouldBe result
        }
    }
})
