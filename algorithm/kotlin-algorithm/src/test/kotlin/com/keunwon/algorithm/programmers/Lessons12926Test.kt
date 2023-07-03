package com.keunwon.algorithm.programmers

import io.kotest.core.spec.style.StringSpec
import io.kotest.data.forAll
import io.kotest.data.row
import io.kotest.matchers.shouldBe

internal class Lessons12926Test : StringSpec({
    "case" {
        forAll(
            row("AB", 1, "BC"),
            row("z", 1, "a"),
            row("a B z", 4, "e F d")
        ) { s, n, result ->
            val actual = Lessons12926().solution(s, n)
            actual shouldBe result
        }
    }
})
