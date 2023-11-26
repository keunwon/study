package com.keunwon.algorithm.programmers

import io.kotest.core.spec.style.StringSpec
import io.kotest.data.forAll
import io.kotest.data.row
import io.kotest.matchers.shouldBe

internal class Lessons43236Test : StringSpec({
    "기본예제" {
        forAll(
            row(25, intArrayOf(2, 14, 11, 21, 17), 2, 4),
        ) { distance, rocks, n, result ->
            val actual = Lessons43236().solution(distance, rocks, n)
            actual shouldBe result
        }
    }
})
