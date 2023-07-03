package com.keunwon.algorithm.programmers

import io.kotest.core.spec.style.StringSpec
import io.kotest.data.forAll
import io.kotest.data.row
import io.kotest.matchers.shouldBe

internal class Lessons12939Test : StringSpec({
    "case" {
        forAll(
            row("1 2 3 4", "1 4"),
            row("-1 -2 -3 -4", "-4 -1"),
            row("-1 -1", "-1 -1"),
        ) { s, result ->
            val actual = Lessons12939().solution(s)
            actual shouldBe result
        }
    }
})
