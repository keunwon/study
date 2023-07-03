package com.keunwon.algorithm.programmers

import io.kotest.core.spec.style.StringSpec
import io.kotest.data.forAll
import io.kotest.data.row
import io.kotest.matchers.shouldBe

internal class Lessons12935Test : StringSpec({
    "case" {
        forAll(
            row(intArrayOf(4, 3, 2, 1), intArrayOf(4, 3, 2)),
            row(intArrayOf(10), intArrayOf(-1)),
        ) { arr, result ->
            val actual = Lessons12935().solution(arr)
            actual shouldBe result
        }
    }
})
