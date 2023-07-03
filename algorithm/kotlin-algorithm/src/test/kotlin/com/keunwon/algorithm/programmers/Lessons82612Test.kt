package com.keunwon.algorithm.programmers

import io.kotest.core.spec.style.StringSpec
import io.kotest.data.forAll
import io.kotest.data.row
import io.kotest.matchers.shouldBe

internal class Lessons82612Test : StringSpec({
    "case" {
        forAll(
            row(3, 20, 4, 10),
        ) { price, money, count, result ->
            val actual = Lessons82612().solution(price, money, count)
            actual shouldBe result
        }
    }
})
