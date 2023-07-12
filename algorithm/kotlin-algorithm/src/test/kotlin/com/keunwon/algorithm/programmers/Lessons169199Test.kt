package com.keunwon.algorithm.programmers

import io.kotest.core.spec.style.StringSpec
import io.kotest.data.blocking.forAll
import io.kotest.data.row
import io.kotest.matchers.shouldBe

internal class Lessons169199Test : StringSpec({
    "case" {
        forAll(
            row(arrayOf("...D..R", ".D.G...", "....D.D", "D....D.", "..D...."), 7),
            row(arrayOf(".D.R", "....", ".G..", "...D"), -1),
        ) { board, result ->
            val actual = Lessons169199().solution(board)
            actual shouldBe result
        }
    }
})
