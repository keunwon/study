package com.keunwon.algorithm.programmers

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

internal class Lessons152996Test : StringSpec({
    "case" {
        val weights = intArrayOf(100, 180, 360, 100, 270)
        val actual = Lessons152996().solution(weights)
        actual shouldBe 4
    }
})
