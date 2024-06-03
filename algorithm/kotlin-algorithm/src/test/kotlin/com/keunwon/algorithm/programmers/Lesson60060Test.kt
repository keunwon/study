package com.keunwon.algorithm.programmers

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class Lesson60060Test : StringSpec({
    "case-1" {
        val words = arrayOf("frodo", "front", "frost", "frozen", "frame", "kakao")
        val queries = arrayOf("fro??", "????o", "fr???", "fro???", "pro?")

        val actual = Lesson60060().solution(words, queries)

        actual shouldBe intArrayOf(3, 2, 4, 1, 0)
    }
})
