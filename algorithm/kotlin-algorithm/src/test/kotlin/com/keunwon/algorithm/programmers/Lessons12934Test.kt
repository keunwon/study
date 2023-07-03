package com.keunwon.algorithm.programmers

import io.kotest.core.spec.style.StringSpec
import io.kotest.data.forAll
import io.kotest.data.row
import io.kotest.matchers.shouldBe

internal class Lessons12934Test : StringSpec({
    "case" {
        forAll(
            row(121L, 144),
            row(3L, -1),
        ) { n, result ->
            val actual = Lessons12934().solution(n)
            actual shouldBe result
        }
    }
})
