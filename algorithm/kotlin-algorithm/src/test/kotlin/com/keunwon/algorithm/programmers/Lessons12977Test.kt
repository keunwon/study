package com.keunwon.algorithm.programmers

import io.kotest.core.spec.style.StringSpec
import io.kotest.data.forAll
import io.kotest.data.row
import io.kotest.matchers.shouldBe

internal class Lessons12977Test : StringSpec({
    "case" {
        forAll(
            row(intArrayOf(1, 2, 3, 4), 1),
            row(intArrayOf(1, 2, 7, 6, 4), 4),
        ) { nums, result ->
            val actual = Lessons12977().solution(nums)
            actual shouldBe result
        }
    }
})
