package com.keunwon.algorithm.baekjoon

import io.kotest.core.spec.style.StringSpec
import io.kotest.data.forAll
import io.kotest.data.row
import io.kotest.matchers.shouldBe

internal class Problem7490Test : StringSpec({
    "case" {
        forAll(
            row(3, arrayOf("1+2-3")),
            row(7, arrayOf(
                "1+2-3+4-5-6+7",
                "1+2-3-4+5+6-7",
                "1-2 3+4+5+6+7",
                "1-2 3-4 5+6 7",
                "1-2+3+4-5+6-7",
                "1-2-3-4-5+6+7",
            ))
        ) { n, result ->
            val actual = Problem7490().solution(n)
            actual shouldBe result
        }
    }
})
