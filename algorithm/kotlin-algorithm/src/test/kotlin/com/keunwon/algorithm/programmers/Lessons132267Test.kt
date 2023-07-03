package com.keunwon.algorithm.programmers

import io.kotest.core.spec.style.StringSpec
import io.kotest.data.forAll
import io.kotest.data.row
import io.kotest.matchers.shouldBe

internal class Lessons132267Test : StringSpec({
    "case" {
        forAll(
            row(2, 1, 20, 19),
            row(3, 1, 20, 9),
        ) { a, b, n, result ->
            val actual = Lessons132267().solution(a, b, n)
            actual shouldBe result
        }
    }
})
