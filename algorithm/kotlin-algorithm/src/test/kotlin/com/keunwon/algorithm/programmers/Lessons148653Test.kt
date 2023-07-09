package com.keunwon.algorithm.programmers

import io.kotest.core.spec.style.StringSpec
import io.kotest.data.blocking.forAll
import io.kotest.data.row
import io.kotest.matchers.shouldBe

internal class Lessons148653Test : StringSpec({
    "case" {
        forAll(
            row(16, 6),
            row(2554, 16),
        ) { storey, result ->
            val actual = Lessons148653().solution(storey)
            actual shouldBe result
        }
    }
})
