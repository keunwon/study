package com.keunwon.algorithm.programmers

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class Lesson42883Test : StringSpec({
    "case-1" {
        val number = "1924"
        val k = 2

        val actual = Lesson42883().solution(number, k)

        actual shouldBe "94"
    }

    "case-2" {
        val number = "1231234"
        val k = 3

        val actual = Lesson42883().solution(number, k)

        actual shouldBe "3234"
    }

    "case-3" {
        val number = "4177252841"
        val k = 4

        val actual = Lesson42883().solution(number, k)

        actual shouldBe "775841"
    }
})
