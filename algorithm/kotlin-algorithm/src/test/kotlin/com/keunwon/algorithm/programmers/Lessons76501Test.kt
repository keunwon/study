package com.keunwon.algorithm.programmers

import io.kotest.core.spec.style.StringSpec
import io.kotest.data.forAll
import io.kotest.data.row
import io.kotest.matchers.shouldBe

internal class Lessons76501Test : StringSpec({
    "case" {
        forAll(
            row(intArrayOf(4, 7, 12), booleanArrayOf(true, false, true), 9),
            row(intArrayOf(1, 2, 3), booleanArrayOf(false, false, true), 0),
        ) { absolutes, signs, result ->
            val actual = Lessons76501().solution(absolutes, signs)
            actual shouldBe result
        }
    }
})
