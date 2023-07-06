package com.keunwon.algorithm.programmers

import io.kotest.core.spec.style.StringSpec
import io.kotest.data.forAll
import io.kotest.data.row
import io.kotest.matchers.shouldBe

internal class Lessons92335Test : StringSpec({
    "case" {
        forAll(
            row(437674, 3, 3),
            row(110011, 10, 2),
        ) { n, k, result ->
            val actual = Lessons92335().solution(n, k)
            actual shouldBe result
        }
    }
})
