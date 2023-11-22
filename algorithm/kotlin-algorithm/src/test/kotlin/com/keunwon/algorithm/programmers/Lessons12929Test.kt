package com.keunwon.algorithm.programmers

import io.kotest.core.spec.style.StringSpec
import io.kotest.data.forAll
import io.kotest.data.row
import io.kotest.matchers.shouldBe

internal class Lessons12929Test : StringSpec({
    "기본예제" {
        forAll(
            row(2, 2),
            row(3, 5),
        ) { n, result ->
            val actual = Lessons12929().solution(n)
            actual shouldBe result
        }
    }
})
