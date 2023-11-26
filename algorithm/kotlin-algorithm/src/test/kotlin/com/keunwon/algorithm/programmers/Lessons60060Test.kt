package com.keunwon.algorithm.programmers

import io.kotest.core.spec.style.StringSpec
import io.kotest.data.blocking.forAll
import io.kotest.data.row
import io.kotest.matchers.shouldBe

internal class Lessons60060Test : StringSpec({
    "기본예제" {
        forAll(
            row(
                arrayOf("frodo", "front", "frost", "frozen", "frame", "kakao"),
                arrayOf("fro??", "????o", "fr???", "fro???", "pro?"),
                intArrayOf(3, 2, 4, 1, 0)
            )
        ) { words, queries, result ->
            val actual = Lessons60060().solution(words, queries)
            actual shouldBe result
        }
    }
})
