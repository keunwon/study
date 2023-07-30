package com.keunwon.algorithm.programmers

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

internal class Lessons76503Test : StringSpec({
    "case_01" {
        val a = intArrayOf(-5, 0, 2, 1, 2)
        val edges = arrayOf(
            intArrayOf(0, 1),
            intArrayOf(3, 4),
            intArrayOf(2, 3),
            intArrayOf(0, 3),
        )

        val actual = Lessons76503().solution(a, edges)

        actual shouldBe 9
    }
})
