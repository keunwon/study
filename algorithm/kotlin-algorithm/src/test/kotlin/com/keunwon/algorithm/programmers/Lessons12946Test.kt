package com.keunwon.algorithm.programmers

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

internal class Lessons12946Test : StringSpec({
    "case_01" {
        val n = 2
        val actual = Lessons12946().solution(n)
        actual shouldBe arrayOf(
            intArrayOf(1, 2),
            intArrayOf(1, 3),
            intArrayOf(2, 3),
        )
    }
})
