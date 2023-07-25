package com.keunwon.algorithm.programmers

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

internal class Lessons152995Test : StringSpec({
    "case_01" {
        val scores = arrayOf(
            intArrayOf(2, 2),
            intArrayOf(1, 4),
            intArrayOf(3, 2),
            intArrayOf(3, 2),
            intArrayOf(2, 1),
        )
        val actual = Lessons152995().solution(scores)
        actual shouldBe 4
    }
})
