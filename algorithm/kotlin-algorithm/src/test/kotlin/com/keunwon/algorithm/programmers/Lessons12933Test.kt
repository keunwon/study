package com.keunwon.algorithm.programmers

import io.kotest.core.spec.style.StringSpec
import io.kotest.data.forAll
import io.kotest.data.row
import io.kotest.matchers.shouldBe

internal class Lessons12933Test : StringSpec({
    "case" {
        forAll(
            row(118372L, 873211)
        ) { n, result ->
            val actual = Lessons12933().solution(n)
            actual shouldBe result
        }
    }
})
