package com.keunwon.algorithm.programmers

import io.kotest.core.spec.style.StringSpec
import io.kotest.data.forAll
import io.kotest.data.row
import io.kotest.matchers.shouldBe

internal class Lessons42891Test : StringSpec({
    "기본예제" {
        forAll(
            row(intArrayOf(3, 1, 2), 5L, 1L),
        ) { food_times, k, result ->
            val actual = Lessons42891().solution(food_times, k)
            actual shouldBe result
        }
    }
})
