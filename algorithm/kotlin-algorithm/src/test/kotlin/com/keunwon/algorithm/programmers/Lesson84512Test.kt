package com.keunwon.algorithm.programmers

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class Lesson84512Test : StringSpec({
    "case-1" {
        val word = "AAAAE"
        val actual = Lesson84512().solution(word)
        actual shouldBe 6
    }

    "case-2" {
        val word = "AAAE"
        val actual = Lesson84512().solution(word)
        actual shouldBe 10
    }

    "case-3" {
        val word = "I"
        val actual = Lesson84512().solution(word)
        actual shouldBe 1563
    }

    "case-4" {
        val word = "EIO"
        val actual = Lesson84512().solution(word)
        actual shouldBe 1189
    }
})
