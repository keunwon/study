package com.keunwon.algorithm.programmers

import io.kotest.core.spec.style.StringSpec
import io.kotest.data.forAll
import io.kotest.data.row
import io.kotest.matchers.shouldBe

internal class Lessons154539Test : StringSpec({
    "case" {
        forAll(
            row(intArrayOf(2, 3, 3, 5), intArrayOf(3, 5, 5, -1)),
            row(intArrayOf(9, 1, 5, 3, 6, 2), intArrayOf(-1, 5, 6, 6, -1, -1)),
        ) { numbers, result ->
            val actual = Lessons154539().solution(numbers)
            actual shouldBe result
        }
    }
})
