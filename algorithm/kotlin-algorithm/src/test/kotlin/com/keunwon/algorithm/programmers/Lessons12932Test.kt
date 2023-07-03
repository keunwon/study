package com.keunwon.algorithm.programmers

import io.kotest.core.spec.style.StringSpec
import io.kotest.data.forAll
import io.kotest.data.row
import io.kotest.matchers.shouldBe

internal class Lessons12932Test : StringSpec({
    "case" {
        forAll(
            row(12345L, intArrayOf(5, 4, 3, 2, 1)),
        ) { n, result ->
            val actual = Lessons12932().solution(n)
            actual shouldBe result
        }
    }
})
