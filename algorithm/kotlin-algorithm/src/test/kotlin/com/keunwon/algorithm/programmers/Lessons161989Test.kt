package com.keunwon.algorithm.programmers

import io.kotest.core.spec.style.StringSpec
import io.kotest.data.forAll
import io.kotest.data.row
import io.kotest.matchers.shouldBe

internal class Lessons161989Test : StringSpec({
    "case" {
        forAll(
            row(8, 4, intArrayOf(2, 3, 6), 2),
            row(5, 4, intArrayOf(1, 3), 1),
            row(4, 1, intArrayOf(1, 2, 3, 4), 4),
        ) { n, m, section, result ->
            val actual = Lessons161989().solution(n, m, section)
            actual shouldBe result
        }
    }
})
