package com.keunwon.algorithm.programmers

import io.kotest.core.spec.style.StringSpec
import io.kotest.data.forAll
import io.kotest.data.row
import io.kotest.matchers.shouldBe

internal class Lessons76502Test : StringSpec({
    "case" {
        forAll(
            row("[](){}", 3),
            row("}]()[{", 2),
            row("[)(]", 0),
            row("}}}", 0),
        ) { s, result ->
            val actual = Lessons76502().solution(s)
            actual shouldBe result
        }
    }
})
