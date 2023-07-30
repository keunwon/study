package com.keunwon.algorithm.programmers

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

internal class Lessons138475Test : StringSpec({
    "case_01" {
        val e = 8
        val starts = intArrayOf(1, 3, 7)

        val actual = Lessons138475().solution(e, starts)

        actual shouldBe intArrayOf(6, 6, 8)
    }
})
