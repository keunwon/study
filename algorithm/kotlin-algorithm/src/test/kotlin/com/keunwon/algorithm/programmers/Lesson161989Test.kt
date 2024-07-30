package com.keunwon.algorithm.programmers

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class Lesson161989Test : StringSpec({
    "case-1" {
        val n = 8
        val m = 4
        val section = intArrayOf(2, 3, 6)

        val actual = Lesson161989().solution(n, m, section)

        actual shouldBe 2
    }

    "case-2" {
        val n = 5
        val m = 4
        val section = intArrayOf(1, 3)

        val actual = Lesson161989().solution(n, m, section)

        actual shouldBe 1
    }

    "case-3" {
        val n = 4
        val m = 1
        val section = intArrayOf(1, 2, 3, 4)

        val actual = Lesson161989().solution(n, m, section)

        actual shouldBe 4
    }
})
