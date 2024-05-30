package com.keunwon.algorithm.programmers

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class Lesson43238Test : StringSpec({
    "case-1" {
        val n = 6
        val times = intArrayOf(7, 10)

        val actual = Lesson43238().solution(n, times)

        actual shouldBe 28
    }
})
