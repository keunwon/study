package com.keunwon.algorithm.programmers

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

internal class Lessons135808Test : StringSpec({
    "case_01" {
        val k = 3
        val m = 4
        val score = intArrayOf(1, 2, 3, 1, 2, 3, 1)

        val actual = Lessons135808().solution(k, m, score)

        actual shouldBe 8
    }

    "case_02" {
        val k = 4
        val m = 3
        val score = intArrayOf(4, 1, 2, 2, 4, 4, 4, 4, 1, 2, 4, 2)

        val actual = Lessons135808().solution(k, m, score)

        actual shouldBe 33
    }
})
