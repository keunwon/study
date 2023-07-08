package com.keunwon.algorithm.programmers

import io.kotest.core.spec.style.StringSpec
import io.kotest.data.blocking.forAll
import io.kotest.data.row
import io.kotest.matchers.shouldBe

internal class Lessons60058Test : StringSpec({
    "case" {
        forAll(
            row("(()())()", "(()())()"),
            row(")(", "()"),
            row("()))((()", "()(())()"),
        ) { p, result ->
            val actual = Lessons60058().solution(p)
            actual shouldBe result
        }
    }
})
