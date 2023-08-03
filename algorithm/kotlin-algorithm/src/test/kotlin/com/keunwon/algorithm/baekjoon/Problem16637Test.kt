package com.keunwon.algorithm.baekjoon

import io.kotest.core.spec.style.StringSpec
import io.kotest.data.forAll
import io.kotest.data.row
import io.kotest.matchers.shouldBe

internal class Problem16637Test : StringSpec({
    "case" {
        forAll(
            row("3+8*7-9*2", 136),
            row("8*3+5", 64),
            row("8*3+5+2", 66),
            row("1*2+3*4*5-6*7*8*9*0", 0),
            row("1*2+3*4*5-6*7*8*9*9", 426384),
            row("1-9-1-9-1-9-1-9-1-9", 24),
        ) { expression, result ->
            val actual = Problem16637().solution(expression)
            actual shouldBe result
        }
    }
})
