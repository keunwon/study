package com.keunwon.algorithm.programmers

import io.kotest.core.spec.style.StringSpec
import io.kotest.data.blocking.forAll
import io.kotest.data.row
import io.kotest.matchers.shouldBe

internal class Lessons154540Test : StringSpec({
    "case" {
        forAll(
            row(arrayOf("X591X", "X1X5X", "X231X", "1XXX1"), intArrayOf(1, 1, 27)),
            row(arrayOf("XXX", "XXX", "XXX"), intArrayOf(-1))
        ) { maps, result ->
            val actual = Lessons154540().solution(maps)
            actual shouldBe result
        }
    }
})
