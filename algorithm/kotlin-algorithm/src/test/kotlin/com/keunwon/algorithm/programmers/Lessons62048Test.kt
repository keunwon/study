package com.keunwon.algorithm.programmers

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

internal class Lessons62048Test : StringSpec({
    "case_01" {
        val w = 8
        val h = 12

        val actual = Lessons62048().solution(w, h)

        actual shouldBe 80
    }
})
