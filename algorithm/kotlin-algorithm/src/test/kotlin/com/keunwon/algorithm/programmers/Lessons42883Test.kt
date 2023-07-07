package com.keunwon.algorithm.programmers

import io.kotest.core.spec.style.StringSpec
import io.kotest.data.blocking.forAll
import io.kotest.data.row
import io.kotest.matchers.shouldBe

internal class Lessons42883Test : StringSpec({
    "case" {
        forAll(
            row("1924", 2, "94"),
            row("1231234", 3, "3234"),
            row("4177252841", 4, "775841"),
        ) { number, k, result ->
            val actual = Lessons42883().solution(number, k)
            actual shouldBe result
        }
    }
})
