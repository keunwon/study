package com.keunwon.algorithm.programmers

import io.kotest.core.spec.style.StringSpec
import io.kotest.data.forAll
import io.kotest.data.row
import io.kotest.matchers.shouldBe

internal class Lessons12912Test : StringSpec({
    "case" {
        forAll(
            row(3, 5, 12),
            row(3, 3, 3),
            row(5, 3, 12),
        ) { a, b, result ->
            val actual = Lessons12912().solution(a, b)
            actual shouldBe result
        }
    }
})
