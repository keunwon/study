package com.keunwon.algorithm.programmers

import io.kotest.core.spec.style.StringSpec
import io.kotest.data.forAll
import io.kotest.data.row
import io.kotest.matchers.shouldBe

internal class Lessons12922Test : StringSpec({
    "case" {
        forAll(
            row(3, "수박수"),
            row(4, "수박수박"),
        ) { n, result ->
            val actual = Lessons12922().solution(n)
            actual shouldBe result
        }
    }
})
