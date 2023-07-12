package com.keunwon.algorithm.programmers

import io.kotest.core.spec.style.StringSpec
import io.kotest.data.blocking.forAll
import io.kotest.data.row
import io.kotest.matchers.shouldBe

internal class Lessons60057Test : StringSpec({
    "case" {
        forAll(
            row("aabbaccc", 7),
            row("ababcdcdababcdcd", 9),
            row("abcabcdede", 8),
            row("abcabcabcabcdededededede", 14),
            row("xababcdcdababcdcd", 17),
        ) { s, result ->
            val actual = Lessons60057().solution(s)
            actual shouldBe result
        }
    }
})
