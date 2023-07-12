package com.keunwon.algorithm.programmers

import io.kotest.core.spec.style.StringSpec
import io.kotest.data.blocking.forAll
import io.kotest.data.row
import io.kotest.matchers.shouldBe

internal class Lessons68646Test : StringSpec({
    "case_01" {
        forAll(
            row(intArrayOf(9, -1, -5), 3),
            row(intArrayOf(-16, 27, 65, -2, 58, -92, -71, -68, -61, -33), 6)
        ) { a, result ->
            val actual = Lessons68646().solution(a)
            actual shouldBe result
        }
    }
})
