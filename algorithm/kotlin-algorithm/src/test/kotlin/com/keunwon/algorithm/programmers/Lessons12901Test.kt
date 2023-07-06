package com.keunwon.algorithm.programmers

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

internal class Lessons12901Test : StringSpec({
    "case_01" {
        val a = 5
        val b = 24

        val actual = Lessons12901().solution(a, b)

        actual shouldBe "TUE"
    }
})
