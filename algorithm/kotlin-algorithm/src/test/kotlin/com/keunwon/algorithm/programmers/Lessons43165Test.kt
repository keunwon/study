package com.keunwon.algorithm.programmers

import io.kotest.core.spec.style.StringSpec
import io.kotest.data.forAll
import io.kotest.data.row
import io.kotest.matchers.shouldBe

internal class Lessons43165Test : StringSpec({
    "case" {
        forAll(
            row(intArrayOf(1, 1, 1, 1, 1), 3, 5),
            row(intArrayOf(4, 1, 2, 1), 4, 2),
        ) { numbers, target, result ->
            val actual = Lessons43165().solution(numbers, target)
            actual shouldBe result
        }
    }
})
