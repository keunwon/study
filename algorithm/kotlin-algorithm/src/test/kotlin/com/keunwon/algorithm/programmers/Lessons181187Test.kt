package com.keunwon.algorithm.programmers

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

internal class Lessons181187Test : StringSpec({
    "case_01" {
        val r1 = 2
        val r2 = 3
        val actual = Lessons181187().solution(r1, r2)
        actual shouldBe 20
    }
})
