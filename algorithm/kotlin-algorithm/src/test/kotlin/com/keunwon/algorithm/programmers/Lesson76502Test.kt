package com.keunwon.algorithm.programmers

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class Lesson76502Test : StringSpec({
    "case-1" {
        val s = "[](){}"
        val actual = Lesson76502().solution(s)
        actual shouldBe 3
    }

    "case-2" {
        val s = "}]()[{"
        val actual = Lesson76502().solution(s)
        actual shouldBe 2
    }

    "case-3" {
        val s = "[)(]"
        val actual = Lesson76502().solution(s)
        actual shouldBe 0
    }

    "case-4" {
        val s = "}}}"
        val actual = Lesson76502().solution(s)
        actual shouldBe 0
    }
})
