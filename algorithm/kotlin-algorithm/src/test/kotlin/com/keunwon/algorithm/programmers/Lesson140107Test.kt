package com.keunwon.algorithm.programmers

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class Lesson140107Test : StringSpec({
    "case-1" {
        val k = 2
        val d = 4

        val actual = Lesson140107().solution(k, d)

        actual shouldBe 6
    }

    "case-2" {
        val k = 1
        val d = 5

        val actual = Lesson140107().solution(k, d)

        actual shouldBe 26
    }
})
