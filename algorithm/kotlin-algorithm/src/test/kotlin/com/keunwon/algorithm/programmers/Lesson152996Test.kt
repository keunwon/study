package com.keunwon.algorithm.programmers

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class Lesson152996Test : StringSpec({
    "case-1" {
        val weights = intArrayOf(100, 180, 360, 100, 270)
        val actual = Lesson152996().solution(weights)
        actual shouldBe 4
    }
})