package com.keunwon.algorithm.programmers

import io.kotest.core.spec.style.StringSpec
import io.kotest.data.forAll
import io.kotest.data.row
import io.kotest.matchers.shouldBe

internal class Lessons12954Test : StringSpec({
    "case" {
        forAll(
            row(2, 5, longArrayOf(2, 4, 6, 8, 10)),
            row(4, 3, longArrayOf(4, 8, 12)),
            row(-4, 2, longArrayOf(-4, -8)),
        ) { x, n, result ->
            val actual = Lessons12954().solution(x, n)
            actual shouldBe result
        }
    }
})
