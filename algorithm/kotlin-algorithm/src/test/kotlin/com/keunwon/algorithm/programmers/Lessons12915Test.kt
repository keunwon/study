package com.keunwon.algorithm.programmers

import io.kotest.core.spec.style.StringSpec
import io.kotest.data.forAll
import io.kotest.data.row
import io.kotest.matchers.shouldBe

internal class Lessons12915Test : StringSpec({
    "case" {
        forAll(
            row(arrayOf("sun", "bed", "car"), 1, arrayOf("car", "bed", "sun")),
            row(arrayOf("abce", "abcd", "cdx"), 2, arrayOf("abcd", "abce", "cdx")),
        ) { strings, n, result ->
            val actual = Lessons12915().solution(strings, n)
            actual shouldBe result
        }
    }
})
