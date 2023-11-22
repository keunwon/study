package com.keunwon.algorithm.programmers

import io.kotest.core.spec.style.StringSpec
import io.kotest.data.forAll
import io.kotest.data.row
import io.kotest.matchers.shouldBe

internal class Lessons150369Test : StringSpec({
    "기본예제" {
        forAll(
            row(4, 5, intArrayOf(1, 0, 3, 1, 2), intArrayOf(0, 3, 0, 4, 0), 16),
            row(2, 7, intArrayOf(1, 0, 2, 0, 1, 0, 2), intArrayOf(0, 2, 0, 1, 0, 2, 0), 30)
        ) { cap, n, deliveries, pickups, result ->
            val actual = Lessons150369().solution(cap, n, deliveries, pickups)
            actual shouldBe result
        }
    }
})
