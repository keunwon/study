package com.keunwon.algorithm.programmers

import io.kotest.core.spec.style.StringSpec
import io.kotest.data.forAll
import io.kotest.data.row
import io.kotest.matchers.shouldBe

internal class Lessons42583Test : StringSpec({
    "case" {
        forAll(
            row(2, 10, intArrayOf(7, 4, 5, 6), 8),
            row(100, 100, intArrayOf(10), 101),
            row(100, 100, intArrayOf(10, 10, 10, 10, 10, 10, 10, 10, 10, 10), 110),
        ) { bridge_length, weight, truck_weights, result ->
            val actual = Lessons42583().solution(bridge_length, weight, truck_weights)
            actual shouldBe result
        }
    }
})
