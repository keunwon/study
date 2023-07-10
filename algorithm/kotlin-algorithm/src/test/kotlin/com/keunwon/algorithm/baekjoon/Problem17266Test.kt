package com.keunwon.algorithm.baekjoon

import io.kotest.core.spec.style.StringSpec
import io.kotest.data.blocking.forAll
import io.kotest.data.row
import io.kotest.matchers.shouldBe

internal class Problem17266Test : StringSpec({
    "case" {
        forAll(
            row(5, intArrayOf(2, 4), 2),
            row(3, intArrayOf(0), 3),
        ) { n, arr, result ->
            val actual = Problem17266().solution(n, arr)
            actual shouldBe result
        }
    }
})
