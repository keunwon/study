package com.keunwon.algorithm.programmers

import io.kotest.core.spec.style.StringSpec
import io.kotest.data.forAll
import io.kotest.data.row
import io.kotest.matchers.shouldBe

internal class Lessons70129Test : StringSpec({
    "case" {
        forAll(
            row("110010101001", intArrayOf(3, 8)),
            row("01110", intArrayOf(3, 3)),
            row("1111111", intArrayOf(4, 1)),
        ) { s, result ->
            val actual = Lessons70129().solution(s)
            actual shouldBe result
        }
    }
})
