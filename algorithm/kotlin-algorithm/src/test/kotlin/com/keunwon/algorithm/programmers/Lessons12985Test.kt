package com.keunwon.algorithm.programmers

import io.kotest.core.spec.style.StringSpec
import io.kotest.data.forAll
import io.kotest.data.row
import io.kotest.matchers.shouldBe

internal class Lessons12985Test : StringSpec({
    "case" {
        forAll(
            row(8, 4, 7, 3),
        ) { n, a, b, result ->
            val actual = Lessons12985().solution(n, a, b)
            actual shouldBe result
        }
    }
})
