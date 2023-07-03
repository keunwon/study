package com.keunwon.algorithm.programmers

import io.kotest.core.spec.style.StringSpec
import io.kotest.data.forAll
import io.kotest.data.row
import io.kotest.matchers.shouldBe

internal class Lessons81301Test : StringSpec({
    "case" {
        forAll(
            row("one4seveneight", 1478),
            row("23four5six7", 234567),
            row("2three45sixseven", 234567),
            row("123", 123),
        ) { s, result ->
            val actual = Lessons81301().solution(s)
            actual shouldBe result
        }
    }
})
