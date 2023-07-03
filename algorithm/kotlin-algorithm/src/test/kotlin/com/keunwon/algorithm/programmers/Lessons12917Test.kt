package com.keunwon.algorithm.programmers

import io.kotest.core.spec.style.StringSpec
import io.kotest.data.forAll
import io.kotest.data.row
import io.kotest.matchers.shouldBe

internal class Lessons12917Test : StringSpec({
    "case" {
        forAll(
            row("Zbcdefg", "gfedcbZ"),
        ) { s, result ->
            val actual = Lessons12917().solution(s)
            actual shouldBe result
        }
    }
})
