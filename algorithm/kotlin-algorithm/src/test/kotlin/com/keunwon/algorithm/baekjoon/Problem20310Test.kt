package com.keunwon.algorithm.baekjoon

import io.kotest.core.spec.style.StringSpec
import io.kotest.data.forAll
import io.kotest.data.row
import io.kotest.matchers.shouldBe

internal class Problem20310Test : StringSpec({
    "case" {
        forAll(
            row("1010", "01"),
            row("000011", "001"),
        ) { binary, result ->
            val actual = Problem20310().solution(binary)
            actual shouldBe result
        }
    }
})
