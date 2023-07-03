package com.keunwon.algorithm.programmers

import io.kotest.core.spec.style.StringSpec
import io.kotest.data.forAll
import io.kotest.data.row
import io.kotest.matchers.shouldBe

internal class Lessons68935Test : StringSpec({
    "case" {
        forAll(
            row(45, 7),
            row(125, 229),
        ) { n, result ->
            val actual = Lessons68935().solution(n)
            actual shouldBe result
        }
    }
})
