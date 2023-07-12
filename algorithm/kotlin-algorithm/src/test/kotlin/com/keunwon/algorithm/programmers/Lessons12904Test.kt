package com.keunwon.algorithm.programmers

import io.kotest.core.spec.style.StringSpec
import io.kotest.data.blocking.forAll
import io.kotest.data.row
import io.kotest.matchers.shouldBe

internal class Lessons12904Test : StringSpec({
    "case" {
        forAll(
            row("abcdcba", 7),
            row("abacde", 3),
        ) { s, result ->
            val actual = Lessons12904().solution(s)
            actual shouldBe result
        }
    }
})
