package com.keunwon.algorithm.programmers

import io.kotest.core.spec.style.StringSpec
import io.kotest.data.blocking.forAll
import io.kotest.data.row
import io.kotest.matchers.shouldBe

internal class Lessons67258Test : StringSpec({
    "case" {
        forAll(
            row(arrayOf("DIA", "RUBY", "RUBY", "DIA", "DIA", "EMERALD", "SAPPHIRE", "DIA"), intArrayOf(3, 7)),
            row(arrayOf("AA", "AB", "AC", "AA", "AC"), intArrayOf(1, 3)),
            row(arrayOf("XYZ", "XYZ", "XYZ"), intArrayOf(1, 1)),
            row(arrayOf("ZZZ", "YYY", "NNNN", "YYY", "BBB"), intArrayOf(1, 5)),
        ) { gems, result ->
            val actual = Lessons67258().solution(gems)
            actual shouldBe result
        }
    }
})
