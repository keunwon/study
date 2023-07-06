package com.keunwon.algorithm.programmers

import io.kotest.core.spec.style.StringSpec
import io.kotest.data.forAll
import io.kotest.data.row
import io.kotest.matchers.shouldBe

internal class Lessons84512Test : StringSpec({
    "case" {
        forAll(
            row("AAAAE", 6),
            row("AAAE", 10),
            row("I", 1563),
            row("EIO", 1189),
        ) { word, result ->
            val actual = Lessons84512().solution(word)

            actual shouldBe result
        }
    }
})
