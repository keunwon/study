package com.keunwon.algorithm.programmers

import io.kotest.core.spec.style.StringSpec
import io.kotest.data.forAll
import io.kotest.data.row
import io.kotest.matchers.shouldBe

internal class Lessons12940Test : StringSpec({
    "case" {
        forAll(
            row(3, 12, intArrayOf(3, 12)),
            row(2, 5, intArrayOf(1, 10)),
        ) { n, m, result ->
            val actual = Lessons12940().solution(n, m)
            actual shouldBe result
        }
    }
})
