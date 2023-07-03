package com.keunwon.algorithm.programmers

import io.kotest.core.spec.style.StringSpec
import io.kotest.data.forAll
import io.kotest.data.row
import io.kotest.matchers.shouldBe

internal class Lessons86051Test : StringSpec({
    "case" {
        forAll(
            row(intArrayOf(1, 2, 3, 4, 6, 7, 8, 0), 14),
            row(intArrayOf(5, 8, 4, 0, 6, 7, 9), 6),
        ) { numbers, result ->
            val actual = Lessons86051().solution(numbers)
            actual shouldBe result
        }
    }
})
