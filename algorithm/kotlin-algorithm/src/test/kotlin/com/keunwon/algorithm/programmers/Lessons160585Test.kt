package com.keunwon.algorithm.programmers

import io.kotest.core.spec.style.StringSpec
import io.kotest.data.forAll
import io.kotest.data.row
import io.kotest.matchers.shouldBe

internal class Lessons160585Test : StringSpec({
    "case" {
        forAll(
            row(arrayOf("O.X", ".O.", "..X"), 11),
            row(arrayOf("OOO", "...", "XXX"), 0),
            row(arrayOf("...", ".X.", "..."), 0),
        ) { board, result ->
            val actual = Lessons160585().solution(board)
            actual shouldBe result
        }
    }
})
