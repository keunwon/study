package com.keunwon.algorithm.programmers

import io.kotest.core.spec.style.StringSpec
import io.kotest.data.blocking.forAll
import io.kotest.data.row
import io.kotest.matchers.shouldBe

internal class Lessons140107Test : StringSpec({
    "case" {
        forAll(
            row(2, 4, 6),
            //row(1, 5, 26),
        ) { k, d, result ->
            val actual = Lessons140107().solution(k, d)
            actual shouldBe result
        }
    }
})
