package com.keunwon.algorithm.programmers

import io.kotest.core.spec.style.StringSpec
import io.kotest.data.forAll
import io.kotest.data.row
import io.kotest.matchers.shouldBe

internal class Lessons136798Test : StringSpec({
    "case" {
        forAll(
            row(5, 3, 2, 10),
            row(10, 3, 2, 21),
        ) { number, limit, power, result ->
            val actual = Lessons136798().solution(number, limit, power)
            actual shouldBe result
        }
    }
})
