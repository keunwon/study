package com.keunwon.algorithm.baekjoon

import io.kotest.core.spec.style.StringSpec
import io.kotest.data.forAll
import io.kotest.data.row
import io.kotest.matchers.shouldBe

internal class Problem9095Test : StringSpec({
    "case" {
        forAll(
            row(4, 7),
            row(7, 44),
            row(10, 274),
        ) { n, result ->
            dp[n] shouldBe result
        }
    }
}) {
    companion object {
        private val dp = Problem9095().solution()
    }
}
