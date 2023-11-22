package com.keunwon.algorithm.programmers

import io.kotest.core.spec.style.StringSpec
import io.kotest.data.forAll
import io.kotest.data.row
import io.kotest.matchers.shouldBe

internal class Lessons64063Test : StringSpec({
    "기본 테스트" {
        forAll(
            row(10L, longArrayOf(1, 3, 4, 1, 3, 1), longArrayOf(1, 3, 4, 2, 5, 6)),
            row(3L, longArrayOf(1, 1, 1), longArrayOf(1, 2, 3)),
        ) { k, roomNumbers, result ->
            val actual = Lessons64063().solution(k, roomNumbers)
            actual shouldBe result
        }
    }
})
