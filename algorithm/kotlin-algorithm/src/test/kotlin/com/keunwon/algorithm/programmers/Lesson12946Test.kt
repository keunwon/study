package com.keunwon.algorithm.programmers

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class Lesson12946Test : StringSpec({
    "case-1" {
        val n = 2
        val actual = Lesson12946().solution(n)
        actual shouldBe arrayOf(
            intArrayOf(1, 2),
            intArrayOf(1, 3),
            intArrayOf(2, 3),
        )
    }
})
