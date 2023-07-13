package com.keunwon.algorithm.programmers

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

internal class Lessons60061Test : StringSpec({
    "case_01" {
        val n = 5
        val build_frame = arrayOf(
            intArrayOf(1, 0, 0, 1),
            intArrayOf(1, 1, 1, 1),
            intArrayOf(2, 1, 0, 1),
            intArrayOf(2, 2, 1, 1),
            intArrayOf(5, 0, 0, 1),
            intArrayOf(5, 1, 0, 1),
            intArrayOf(4, 2, 1, 1),
            intArrayOf(3, 2, 1, 1),
        )

        val actual = Lessons60061().solution(n, build_frame)

        actual shouldBe arrayOf(
            intArrayOf(1, 0, 0),
            intArrayOf(1, 1, 1),
            intArrayOf(2, 1, 0),
            intArrayOf(2, 2, 1),
            intArrayOf(3, 2, 1),
            intArrayOf(4, 2, 1),
            intArrayOf(5, 0, 0),
            intArrayOf(5, 1, 0),
        )
    }

    // [[],[],[],[],[],[],[],[],[],[]]	[[],[],[],[],[],[]]
    "case_2" {
        val n = 5
        val build_frame = arrayOf(
            intArrayOf(0, 0, 0, 1),
            intArrayOf(2, 0, 0, 1),
            intArrayOf(4, 0, 0, 1),
            intArrayOf(0, 1, 1, 1),
            intArrayOf(1, 1, 1, 1),
            intArrayOf(2, 1, 1, 1),
            intArrayOf(3, 1, 1, 1),
            intArrayOf(2, 0, 0, 0),
            intArrayOf(1, 1, 1, 0),
            intArrayOf(2, 2, 0, 1),
        )

        val actual = Lessons60061().solution(n, build_frame)

        actual shouldBe arrayOf(
            intArrayOf(0, 0, 0),
            intArrayOf(0, 1, 1),
            intArrayOf(1, 1, 1),
            intArrayOf(2, 1, 1),
            intArrayOf(3, 1, 1),
            intArrayOf(4, 0, 0),
        )
    }
})
