package com.keunwon.algorithm.programmers

import io.kotest.core.spec.style.StringSpec
import io.kotest.data.forAll
import io.kotest.data.row
import io.kotest.matchers.shouldBe

internal class Lessons42746Test : StringSpec({
    "case" {
        forAll(
            row(intArrayOf(6, 10, 2), "6210"),
            row(intArrayOf(3, 30, 34, 5, 9), "9534330"),
        ) { numbers, result ->
            val actual = Lessons42746().solution(numbers)
            actual shouldBe result
        }
    }
})
