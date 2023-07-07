package com.keunwon.algorithm.programmers

import io.kotest.core.spec.style.StringSpec
import io.kotest.data.forAll
import io.kotest.data.row
import io.kotest.matchers.shouldBe

internal class Lessons42839Test : StringSpec({
    "case" {
        forAll(
            row("17", 3),
            row("011", 2),
        ) { numbers, result ->
            val actual = Lessons42839().solution(numbers)
            actual shouldBe result
        }
    }
})
