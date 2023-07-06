package com.keunwon.algorithm.programmers

import io.kotest.core.spec.style.StringSpec
import io.kotest.data.forAll
import io.kotest.data.row
import io.kotest.matchers.shouldBe

internal class Lessons42587Test : StringSpec({
    "case" {
        forAll(
            row(intArrayOf(2, 1, 3, 2), 2, 1),
            row(intArrayOf(1, 1, 9, 1, 1, 1), 0, 5),
        ) { priorities, location, result ->
            val actual = Lessons42587().solution(priorities, location)
            actual shouldBe result
        }
    }
})
