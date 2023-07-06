package com.keunwon.algorithm.programmers

import io.kotest.core.spec.style.StringSpec
import io.kotest.data.forAll
import io.kotest.data.row
import io.kotest.matchers.shouldBe

internal class Lessons154538Test : StringSpec({
    "case" {
        forAll(
            row(10, 40, 5, 2),
            row(10, 40, 30, 1),
            row(2, 5, 4, -1),
        ) { x, y, n, result ->
            val actual = Lessons154538().solution(x, y, n)
            actual shouldBe result
        }
    }
})
