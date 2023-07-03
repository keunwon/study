package com.keunwon.algorithm.programmers

import io.kotest.core.spec.style.StringSpec
import io.kotest.data.forAll
import io.kotest.data.row
import io.kotest.matchers.shouldBe

internal class Lessons131705Test : StringSpec({
    "case" {
        forAll(
            row(intArrayOf(-2, 3, 0, 2, -5), 2),
            row(intArrayOf(-3, -2, -1, 0, 1, 2, 3), 5),
            row(intArrayOf(-1, 1, -1, 1), 0),
        ) { number, result ->
            val actual = Lessons131705().solution(number)
            actual shouldBe result
        }
    }
})
