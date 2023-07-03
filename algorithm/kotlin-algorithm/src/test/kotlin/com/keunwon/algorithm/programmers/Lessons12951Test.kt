package com.keunwon.algorithm.programmers

import io.kotest.core.spec.style.StringSpec
import io.kotest.data.forAll
import io.kotest.data.row
import io.kotest.matchers.shouldBe

internal class Lessons12951Test : StringSpec({
    "case" {
        forAll(
            row("3people unFollowed me", "3people Unfollowed Me"),
            row("for the last week", "For The Last Week"),
        ) { s, result ->
            val actual = Lessons12951().solution(s)
            actual shouldBe result
        }
    }
})
