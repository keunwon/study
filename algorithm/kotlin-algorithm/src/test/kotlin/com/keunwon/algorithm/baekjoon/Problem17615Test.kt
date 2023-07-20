package com.keunwon.algorithm.baekjoon

import io.kotest.core.spec.style.StringSpec
import io.kotest.data.forAll
import io.kotest.data.row
import io.kotest.matchers.shouldBe

internal class Problem17615Test : StringSpec({
    "case" {
        forAll(
            row("RBBBRBRRR", 2),
            row("BBRBBBBR", 1),
        ) { s, result ->
            val actual = Problem17615().solution(s.toCharArray())
            actual shouldBe result
        }
    }
})
