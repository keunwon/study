package com.keunwon.algorithm.programmers

import io.kotest.core.spec.style.StringSpec
import io.kotest.data.forAll
import io.kotest.data.row
import io.kotest.matchers.shouldBe

internal class Lessons12943Test : StringSpec({
    "case" {
        forAll(
            row(6, 8),
            row(16, 4),
            row(626331, -1)
        ) { num, result ->
            val actual = Lessons12943().solution(num)
            actual shouldBe result
        }
    }
})
