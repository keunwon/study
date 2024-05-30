package com.keunwon.algorithm.programmers

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class Lesson42839Test : StringSpec({
    "case-1" {
        val numbers = "17"
        val actual = Lesson42839().solution(numbers)
        actual shouldBe 3
    }

    "case-2" {
        val numbers = "011"
        val actual = Lesson42839().solution(numbers)
        actual shouldBe 2
    }
})
