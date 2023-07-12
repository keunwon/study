package com.keunwon.algorithm.programmers

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

internal class Lessons12952Test : StringSpec({
    "case_01" {
        val n = 4
        val actual = Lessons12952().solution(n)
        actual shouldBe 2
    }
})
