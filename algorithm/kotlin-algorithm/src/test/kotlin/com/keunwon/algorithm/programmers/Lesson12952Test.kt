package com.keunwon.algorithm.programmers

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class Lesson12952Test : StringSpec({
    "case-1" {
        val n = 4
        val actual = Lesson12952().solution(n)
        actual shouldBe 2
    }
})