package com.keunwon.algorithm.programmers

import io.kotest.core.spec.style.StringSpec
import io.kotest.data.forAll
import io.kotest.data.row
import io.kotest.matchers.shouldBe

internal class Lessons12914Test : StringSpec({
    "case" {
        forAll(
            row(4, 5),
            row(3, 3),
        ) { n, result ->
            val actual = Lessons12914().solution(n)
            actual shouldBe result
        }
    }
})
