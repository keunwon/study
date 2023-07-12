package com.keunwon.algorithm.programmers

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

internal class Lessons60059Test : StringSpec({
    "case_01" {
        val key = arrayOf(
            intArrayOf(0, 0, 0),
            intArrayOf(1, 0, 0),
            intArrayOf(0, 1, 1),
        )
        val lock = arrayOf(
            intArrayOf(1, 1, 1),
            intArrayOf(1, 1, 0),
            intArrayOf(1, 0, 1),
        )

        val actual = Lessons60059().solution(key, lock)

        actual shouldBe true
    }
})
