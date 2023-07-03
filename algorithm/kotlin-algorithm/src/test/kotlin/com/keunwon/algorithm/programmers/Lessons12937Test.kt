package com.keunwon.algorithm.programmers

import io.kotest.core.spec.style.StringSpec
import io.kotest.data.forAll
import io.kotest.data.row
import io.kotest.matchers.shouldBe

internal class Lessons12937Test : StringSpec({
    "case" {
        forAll(
            row(3, "Odd"),
            row(4, "Even")
        ) { num, result ->
            val actual = Lessons12937().solution(num)
            actual shouldBe result
        }
    }
})
