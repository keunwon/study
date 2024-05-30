package com.keunwon.algorithm.programmers

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class Lesson12914Test : StringSpec({
    "case-1" {
        val n = 4
        val actual = Lesson12914().solution(n)
        actual shouldBe 5
    }

    "case-2" {
        val n = 3
        val actual = Lesson12914().solution(n)
        actual shouldBe 3
    }
})
