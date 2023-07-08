package com.keunwon.algorithm.programmers

import io.kotest.core.spec.style.StringSpec
import io.kotest.data.blocking.forAll
import io.kotest.data.row
import io.kotest.matchers.shouldBe

internal class Lessons118666Test : StringSpec({
    "case" {
        forAll(
            row(arrayOf("AN", "CF", "MJ", "RT", "NA"), intArrayOf(5, 3, 2, 7, 5), "TCMA"),
            row(arrayOf("TR", "RT", "TR"), intArrayOf(7, 1, 3), "RCJA"),
        ) { survey, choices, result ->
            val actual = Lessons118666().solution(survey, choices)
            actual shouldBe result
        }
    }
})
