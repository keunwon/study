package com.keunwon.algorithm.programmers

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class Lesson60062Test : StringSpec({
    "case-1" {
        val n = 12
        val weak = intArrayOf(1, 5, 6, 10)
        val dist = intArrayOf(1, 2, 3, 4)

        val actual = Lesson60062().solution(n, weak, dist)

        actual shouldBe 2
    }

    "case-2" {
        val n = 12
        val weak = intArrayOf(1, 3, 4, 9, 10)
        val dist = intArrayOf(3, 5, 7)

        val actual = Lesson60062().solution(n, weak, dist)

        actual shouldBe 1
    }
})
