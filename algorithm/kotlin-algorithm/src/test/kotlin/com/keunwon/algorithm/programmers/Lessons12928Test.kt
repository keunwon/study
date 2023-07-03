package com.keunwon.algorithm.programmers

import io.kotest.core.spec.style.StringSpec
import io.kotest.data.forAll
import io.kotest.data.row
import io.kotest.matchers.shouldBe

internal class Lessons12928Test : StringSpec({
    "case" {
        forAll(
            row(12, 28),
            row(5, 6),
        ) { n, result ->
            val actual = Lessons12928().solution(n)
            actual shouldBe result
        }
    }
})
