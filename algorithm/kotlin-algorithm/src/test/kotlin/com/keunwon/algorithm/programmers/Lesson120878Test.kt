package com.keunwon.algorithm.programmers

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class Lesson120878Test : StringSpec({
    "case-1" {
        val a = 7
        val b = 20

        val actual = Lesson120878().solution(a, b)

        actual shouldBe 1
    }

    "case-2" {
        val a = 11
        val b = 22

        val actual = Lesson120878().solution(a, b)

        actual shouldBe 1
    }

    "case-3" {
        val a = 12
        val b = 21

        val actual = Lesson120878().solution(a, b)

        actual shouldBe 2
    }
})
