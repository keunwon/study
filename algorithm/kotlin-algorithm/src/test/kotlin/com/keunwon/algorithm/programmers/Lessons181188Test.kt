package com.keunwon.algorithm.programmers

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

internal class Lessons181188Test : StringSpec({
    "case" {
        val targets = arrayOf(
            intArrayOf(4, 5),
            intArrayOf(4, 8),
            intArrayOf(10, 14),
            intArrayOf(11, 13),
            intArrayOf(5, 12),
            intArrayOf(3, 7),
            intArrayOf(1, 4),
        )

        val actual = Lessons181188().solution(targets)

        actual shouldBe 3
    }
})
