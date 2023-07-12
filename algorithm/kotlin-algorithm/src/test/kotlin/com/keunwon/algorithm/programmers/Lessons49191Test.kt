package com.keunwon.algorithm.programmers

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

internal class Lessons49191Test : StringSpec({
    "case_01" {
        val n = 5
        val results = arrayOf(
            intArrayOf(4, 3),
            intArrayOf(4, 2),
            intArrayOf(3, 2),
            intArrayOf(1, 2),
            intArrayOf(2, 5),
        )

        val actual = Lessons49191().solution(n, results)

        actual shouldBe 2
    }
})
