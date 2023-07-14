package com.keunwon.algorithm.baekjoon

import io.kotest.core.spec.style.StringSpec
import io.kotest.data.forAll
import io.kotest.data.row
import io.kotest.matchers.shouldBe

internal class Problem1515Test : StringSpec({
    "case" {
        forAll(
            row("1234", 4),
            row("234092", 20),
            row("999909", 49),
            row("82340329923", 43),
            row("32098221", 61),
            row("1111111", 14),
            row("00000000000000000000000000000000000000000000000000000000000000000000000", 400),
            row("345029834023049820394802334909240982039842039483294792934790209", 279),
        ) { n, result ->
            val actual = Problem1515().solution(n)
            actual shouldBe result
        }
    }
})
