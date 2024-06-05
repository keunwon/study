package com.keunwon.algorithm.programmers

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class Lesson258705Test : StringSpec({
    "case-1" {
        val n = 4
        val tops = intArrayOf(1, 1, 0, 1)

        val actual = Lesson258705().solution(n, tops)

        actual shouldBe 149
    }

    "case-2" {
        val n = 2
        val tops = intArrayOf(0, 1)

        val actual = Lesson258705().solution(n, tops)

        actual shouldBe 11
    }

    "case-3" {
        val n = 10
        val tops = intArrayOf(0, 0, 0, 0, 0, 0, 0, 0, 0, 0)

        val actual = Lesson258705().solution(n, tops)

        actual shouldBe 7704
    }
})
