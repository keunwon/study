package com.keunwon.algorithm.baekjoon

import io.kotest.core.spec.style.StringSpec
import io.kotest.data.forAll
import io.kotest.data.row
import io.kotest.matchers.shouldBe

internal class Problem9935Test : StringSpec({
    "case" {
        forAll(
            row("mirkovC4nizCC44", "C4", "mirkovniz"),
            row("12ab112ab2ab", "12ab", "FRULA"),
        ) { line, remove, result ->
            val actual = Problem9935().solution(line, remove)
            actual shouldBe result
        }
    }
})
