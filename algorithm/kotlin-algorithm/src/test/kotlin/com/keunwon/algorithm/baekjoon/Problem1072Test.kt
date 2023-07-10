package com.keunwon.algorithm.baekjoon

import io.kotest.core.spec.style.StringSpec
import io.kotest.data.blocking.forAll
import io.kotest.data.row
import io.kotest.matchers.shouldBe

internal class Problem1072Test : StringSpec({
    "case" {
        forAll(
            row(10L, 8L, 1L),
            row(100L, 80L, 6L),
            row(47L, 47L, -1L),
            row(99000L, 0L, 1000L),
            row(1000000000L, 470000000L, 19230770L),
        ) { x, y, result ->
            val actual = Problem1072().solution(x, y)
            actual shouldBe result
        }
    }
})
