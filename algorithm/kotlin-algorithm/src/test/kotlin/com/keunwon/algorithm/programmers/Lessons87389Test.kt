package com.keunwon.algorithm.programmers

import io.kotest.core.spec.style.StringSpec
import io.kotest.data.forAll
import io.kotest.data.row
import io.kotest.matchers.shouldBe

internal class Lessons87389Test : StringSpec({
    "case" {
        forAll(
            row(10, 3),
            row(12, 11),
        ) { n, result ->
            val actual = Lessons87389().solution(n)
            actual shouldBe result
        }
    }
})
