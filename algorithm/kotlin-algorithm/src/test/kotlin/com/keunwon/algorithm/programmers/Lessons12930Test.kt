package com.keunwon.algorithm.programmers

import io.kotest.core.spec.style.StringSpec
import io.kotest.data.forAll
import io.kotest.data.row
import io.kotest.matchers.shouldBe

internal class Lessons12930Test : StringSpec({
    "case" {
        forAll(
            row("try hello world", "TrY HeLlO WoRlD"),
        ) { s, result ->
            val actual = Lessons12930().solution(s)
            actual shouldBe result
        }
    }
})
