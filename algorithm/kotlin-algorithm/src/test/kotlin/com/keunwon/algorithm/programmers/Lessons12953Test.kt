package com.keunwon.algorithm.programmers

import io.kotest.core.spec.style.StringSpec
import io.kotest.data.forAll
import io.kotest.data.row
import io.kotest.matchers.shouldBe

internal class Lessons12953Test : StringSpec({
    "case" {
        forAll(
            row(intArrayOf(2, 6, 8, 14), 168),
            row(intArrayOf(1, 2, 3), 6),
        ) { arr, result ->
            val actual = Lessons12953().solution(arr)
            actual shouldBe result
        }
    }
})
