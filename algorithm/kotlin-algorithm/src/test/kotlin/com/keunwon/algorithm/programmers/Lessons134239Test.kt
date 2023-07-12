package com.keunwon.algorithm.programmers

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

internal class Lessons134239Test : StringSpec({
    "case_01" {
        val k = 5
        val ranges = arrayOf(
            intArrayOf(0, 0),
            intArrayOf(0, -1),
            intArrayOf(2, -3),
            intArrayOf(3, -3),
        )

        val actual = Lessons134239().solution(k, ranges)
       
        actual shouldBe doubleArrayOf(33.0, 31.5, 0.0, -1.0)
    }
})
