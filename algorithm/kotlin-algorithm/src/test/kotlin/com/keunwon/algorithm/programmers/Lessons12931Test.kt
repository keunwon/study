package com.keunwon.algorithm.programmers

import io.kotest.core.spec.style.StringSpec
import io.kotest.data.forAll
import io.kotest.data.row
import io.kotest.matchers.shouldBe

internal class Lessons12931Test : StringSpec({
    "case" {
        forAll(
            row(123, 6),
            row(987, 24),
        ) { n, result ->
            val actual = Lessons12931().solution(n)
            actual shouldBe result
        }
    }
})
