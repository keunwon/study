package com.keunwon.algorithm.programmers

import io.kotest.core.spec.style.StringSpec
import io.kotest.data.forAll
import io.kotest.data.row
import io.kotest.matchers.shouldBe

internal class Lessons147355Test : StringSpec({
    "case" {
        forAll(
            row("3141592", "271", 2),
            row("500220839878", "7", 8),
            row("10203", "15", 3)
        ) { t, p, result ->
            val actual = Lessons147355().solution(t, p)
            actual shouldBe result
        }
    }
})
