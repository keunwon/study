package com.keunwon.algorithm.programmers

import io.kotest.core.spec.style.StringSpec
import io.kotest.data.blocking.forAll
import io.kotest.data.row
import io.kotest.matchers.shouldBe

internal class Lessons12918Test : StringSpec({
    "case" {
        forAll(
            row("a234", false),
            row("1234", true),
        ) { s, result ->
            val actual = Lessons12918().solution(s)
            actual shouldBe result
        }
    }
})
