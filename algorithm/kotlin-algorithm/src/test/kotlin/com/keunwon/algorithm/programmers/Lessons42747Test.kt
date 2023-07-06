package com.keunwon.algorithm.programmers

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

internal class Lessons42747Test : StringSpec({
    "case" {
        val citations = intArrayOf(3, 0, 6, 1, 5)

        val actual = Lessons42747().solution(citations)

        actual shouldBe 3
    }
})
