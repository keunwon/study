package com.keunwon.algorithm.programmers

import io.kotest.core.spec.style.StringSpec
import io.kotest.data.blocking.forAll
import io.kotest.data.row
import io.kotest.matchers.shouldBe

internal class Lessons67257Test : StringSpec({
    "case" {
        forAll(
            row("100-200*300-500+20", 60420),
            row("50*6-3*2", 300),
        ) { expressio, result ->
            val actual = Lessons67257().solution(expressio)
            actual shouldBe result
        }
    }
})
