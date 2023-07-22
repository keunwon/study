package com.keunwon.algorithm.baekjoon

import io.kotest.core.spec.style.StringSpec
import io.kotest.data.forAll
import io.kotest.data.row
import io.kotest.matchers.shouldBe

internal class Problem13900Test : StringSpec({
    "case" {
        forAll(
            row(longArrayOf(2, 3, 4), 26),
            row(longArrayOf(1, 2, 3, 4), 35),
            row(longArrayOf(2, 3, 2, 4), 44),
        ) { arr, result ->
            val actual = Problem13900().solution(arr)
            actual shouldBe result
        }
    }
})
