package com.keunwon.algorithm.programmers

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class Lesson142085Test : StringSpec({
    "case-1" {
        val n = 7
        val k = 3
        val enemy = intArrayOf(4, 2, 4, 5, 3, 3, 1)

        val actual = Lesson142085().solution(n, k, enemy)

        actual shouldBe 5
    }

    "case-2" {
        val n = 2
        val k = 4
        val enemy = intArrayOf(3, 3, 3, 3)

        val actual = Lesson142085().solution(n, k, enemy)

        actual shouldBe 4
    }

    "case-3" {
        val n = 2
        val k = 4
        val enemy = intArrayOf(2)

        val actual = Lesson142085().solution(n, k, enemy)

        actual shouldBe 1
    }

    "case-4" {
        val n = 30
        val k = 0
        val enemy = intArrayOf(1, 2, 3, 4, 5)

        val actual = Lesson142085().solution(n, k, enemy)

        actual shouldBe 5
    }
})
