package com.keunwon.algorithm.programmers

import io.kotest.core.spec.style.StringSpec
import io.kotest.data.row
import io.kotest.matchers.shouldBe
import io.kotest.data.forAll as forAll1

internal class Lessons12945Test : StringSpec({
    "case" {
        forAll1(
            row(3, 2),
            row(5, 5),
        ) { n, result ->
            val actual = Lessons12945().solution(n)
            actual shouldBe result
        }
    }
})
