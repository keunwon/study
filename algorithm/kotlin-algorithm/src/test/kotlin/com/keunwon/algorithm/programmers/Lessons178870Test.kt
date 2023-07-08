package com.keunwon.algorithm.programmers

import io.kotest.core.spec.style.StringSpec
import io.kotest.data.blocking.forAll
import io.kotest.data.row
import io.kotest.matchers.shouldBe

internal class Lessons178870Test : StringSpec({
    "case" {
        forAll(
            row(intArrayOf(1, 2, 3, 4, 5), 7, intArrayOf(2, 3)),
            row(intArrayOf(1, 1, 1, 2, 3, 4, 5), 5, intArrayOf(6, 6)),
            row(intArrayOf(2, 2, 2, 2, 2), 6, intArrayOf(0, 2)),
        ) { sequence, k, result ->
            val actual = Lessons178870().solution(sequence, k)
            actual shouldBe result
        }
    }
})
