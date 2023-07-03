package com.keunwon.algorithm.programmers

import io.kotest.core.spec.style.StringSpec
import io.kotest.data.forAll
import io.kotest.data.row
import io.kotest.matchers.shouldBe

internal class Lessons12947Test : StringSpec({
    "case" {
        forAll(
            row(10, true),
            row(12, true),
            row(11, false),
            row(13, false),
        ) { x, result ->
            val actual = Lessons12947().solution(x)
            actual shouldBe result
        }
    }
})
