package com.keunwon.algorithm.programmers

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class Lesson62048Test : StringSpec({
    "case-1" {
        val w = 8
        val h = 12

        val actual = Lesson62048().solution(w, h)

        actual shouldBe 80
    }
})