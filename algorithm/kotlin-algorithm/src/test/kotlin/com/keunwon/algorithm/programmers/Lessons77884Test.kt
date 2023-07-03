package com.keunwon.algorithm.programmers

import io.kotest.core.spec.style.StringSpec
import io.kotest.data.forAll
import io.kotest.data.row
import io.kotest.matchers.shouldBe

internal class Lessons77884Test : StringSpec({
    "case" {
        forAll(
            row(13, 17, 43),
            row(24, 27, 52),
        ) { left, right, result ->
            val actual = Lessons77884().solution(left, right)
            actual shouldBe result
        }
    }
})
