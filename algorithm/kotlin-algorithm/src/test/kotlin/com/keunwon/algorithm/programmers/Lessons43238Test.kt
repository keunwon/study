package com.keunwon.algorithm.programmers

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

internal class Lessons43238Test : StringSpec({
    "case_01" {
        val n = 6
        val times = intArrayOf(7, 10)

        val actual = Lessons43238().solution(n, times)

        actual shouldBe 28
    }
})
