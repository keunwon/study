package com.keunwon.algorithm.baekjoon

import io.kotest.core.spec.style.StringSpec
import io.kotest.data.forAll
import io.kotest.data.row
import io.kotest.matchers.shouldBe

internal class Problem1138Test : StringSpec({
    "case" {
        forAll(
            row(intArrayOf(2, 1, 1, 0), intArrayOf(4, 2, 1, 3)),
            row(intArrayOf(0, 0, 0, 0, 0), intArrayOf(1, 2, 3, 4, 5)),
            row(intArrayOf(5, 4, 3, 2, 1, 0), intArrayOf(6, 5, 4, 3, 2, 1)),
            row(intArrayOf(6, 1, 1, 1, 2, 0, 0), intArrayOf(6, 2, 3, 4, 7, 5, 1)),
        ) { arr, result ->
            val actual = Problem1138().solution(arr)
            actual shouldBe result
        }
    }
})
