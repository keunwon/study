package com.keunwon.algorithm.programmers

import io.kotest.core.spec.style.StringSpec
import io.kotest.data.blocking.forAll
import io.kotest.data.row
import io.kotest.matchers.shouldBe

internal class Lessons142085Test : StringSpec({
    "case" {
        forAll(
            row(7, 3, intArrayOf(4, 2, 4, 5, 3, 3, 1), 5),
            row(2, 4, intArrayOf(3, 3, 3, 3), 4),
        ) { n, k, enemy, result ->
            val actual = Lessons142085().solution(n, k, enemy)
            actual shouldBe result
        }
    }
})
