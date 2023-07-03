package com.keunwon.algorithm.programmers

import io.kotest.core.spec.style.StringSpec
import io.kotest.data.forAll
import io.kotest.data.row
import io.kotest.matchers.shouldBe

internal class Lessons12903Test : StringSpec({
    "case" {
        forAll(
            row("abcde", "c"),
            row("qwer", "we"),
        ) { s, result ->
            val actual = Lessons12903().solution(s)
            actual shouldBe result
        }
    }
})
