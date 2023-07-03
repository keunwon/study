package com.keunwon.algorithm.programmers

import io.kotest.core.spec.style.StringSpec
import io.kotest.data.forAll
import io.kotest.data.row
import io.kotest.matchers.shouldBe

internal class Lessons12944Test : StringSpec({
    "case" {
        forAll(
            row(intArrayOf(1, 2, 3, 4), 2.5),
            row(intArrayOf(5, 5), 5)
        ) { arr, result ->
            val actual = Lessons12944().solution(arr)
            actual shouldBe result
        }
    }
})
