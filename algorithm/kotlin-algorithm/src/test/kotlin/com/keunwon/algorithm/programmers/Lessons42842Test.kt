package com.keunwon.algorithm.programmers

import io.kotest.core.spec.style.StringSpec
import io.kotest.data.forAll
import io.kotest.data.row
import io.kotest.matchers.shouldBe

internal class Lessons42842Test : StringSpec({
    "case" {
        forAll(
            row(10, 2, intArrayOf(4, 3)),
            row(8, 1, intArrayOf(3, 3)),
            row(24, 24, intArrayOf(8, 6)),
        ) { brown, yellow, result ->
            val actual = Lessons42842().solution(brown, yellow)
            actual shouldBe result
        }
    }
})
