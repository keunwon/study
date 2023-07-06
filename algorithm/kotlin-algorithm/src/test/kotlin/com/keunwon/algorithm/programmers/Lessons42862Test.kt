package com.keunwon.algorithm.programmers

import io.kotest.core.spec.style.StringSpec
import io.kotest.data.forAll
import io.kotest.data.row
import io.kotest.matchers.shouldBe

internal class Lessons42862Test : StringSpec({
    "case" {
        forAll(
            row(5, intArrayOf(2, 4), intArrayOf(1, 3, 5), 5),
            row(5, intArrayOf(2, 4), intArrayOf(3), 4),
            row(3, intArrayOf(3), intArrayOf(1), 2),
        ) { n, lost, reserve, result ->
            val actual = Lessons42862().solution(n, lost, reserve)
            actual shouldBe result
        }
    }
})
