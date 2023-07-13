package com.keunwon.algorithm.programmers

import io.kotest.core.spec.style.StringSpec
import io.kotest.data.blocking.forAll
import io.kotest.data.row
import io.kotest.matchers.shouldBe

internal class Lessons42860Test : StringSpec({
    "case" {
        forAll(
            row("JEROEN", 56),
            row("JAN", 23),
        ) { name, result ->
            val actual = Lessons42860().solution(name)
            actual shouldBe result
        }
    }
})
