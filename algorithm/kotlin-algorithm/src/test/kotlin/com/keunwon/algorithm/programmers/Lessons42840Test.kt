package com.keunwon.algorithm.programmers

import io.kotest.core.spec.style.StringSpec
import io.kotest.data.forAll
import io.kotest.data.row
import io.kotest.matchers.shouldBe

internal class Lessons42840Test : StringSpec({
    "case" {
        forAll(
            row(intArrayOf(1, 2, 3, 4, 5), intArrayOf(1)),
            row(intArrayOf(1, 3, 2, 4, 2), intArrayOf(1, 2, 3))
        ) { answers, result ->
            val actual = Lessons42840().solution(answers)
            actual shouldBe result
        }
    }
})
