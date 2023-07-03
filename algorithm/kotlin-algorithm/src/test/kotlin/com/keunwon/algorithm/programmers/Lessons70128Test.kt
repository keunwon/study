package com.keunwon.algorithm.programmers

import io.kotest.core.spec.style.StringSpec
import io.kotest.data.forAll
import io.kotest.data.row
import io.kotest.matchers.shouldBe

internal class Lessons70128Test : StringSpec({
    "case" {
        forAll(
            row(intArrayOf(1, 2, 3, 4), intArrayOf(-3, -1, 0, 2), 3),
            row(intArrayOf(-1, 0, 1), intArrayOf(1, 0, -1), -2),
        ) { a, b, result ->
            val actual = Lessons70128().solution(a, b)
            actual shouldBe result
        }
    }
})
