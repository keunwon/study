package com.keunwon.algorithm.programmers

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class Lesson12929Test : StringSpec({
    "case-1" {
        val n = 2
        val actual = Lesson12929().solution(n)
        actual shouldBe 2
    }

    "case-2" {
        val n = 3
        val actual = Lesson12929().solution(n)
        actual shouldBe 5
    }
})
