package com.keunwon.algorithm.programmers

import io.kotest.core.spec.style.StringSpec
import io.kotest.data.forAll
import io.kotest.data.row
import io.kotest.matchers.shouldBe

internal class Lessons140108Test : StringSpec({
    "case" {
        forAll(
            row("banana", 3),
            row("abracadabra", 6),
            row("aaabbaccccabba", 3),
        ) { s, result ->
            val actual = Lessons140108().solution(s)
            actual shouldBe result
        }
    }
})
