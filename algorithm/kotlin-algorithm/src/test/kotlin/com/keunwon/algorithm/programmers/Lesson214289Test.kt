package com.keunwon.algorithm.programmers

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class Lesson214289Test : StringSpec({
    "case-1" {
        val temperature = 28
        val t1 = 18
        val t2 = 26
        val a = 10
        val b = 8
        val onboard = intArrayOf(0, 0, 1, 1, 1, 1, 1)

        val actual = Lesson214289().solution(temperature, t1, t2, a, b, onboard)

        actual shouldBe 40
    }

    "case-2" {
        val temperature = -10
        val t1 = -5
        val t2 = 5
        val a = 5
        val b = 1
        val onboard = intArrayOf(0, 0, 0, 0, 0, 1, 0)

        val actual = Lesson214289().solution(temperature, t1, t2, a, b, onboard)

        actual shouldBe 25
    }

    "case-3" {
        val temperature = 11
        val t1 = 8
        val t2 = 10
        val a = 10
        val b = 1
        val onboard = intArrayOf(0, 1, 1, 1, 1, 1, 1, 0, 0, 0, 1, 1)

        val actual = Lesson214289().solution(temperature, t1, t2, a, b, onboard)

        actual shouldBe 20
    }

    "case-4" {
        val temperature = 11
        val t1 = 8
        val t2 = 10
        val a = 10
        val b = 100
        val onboard = intArrayOf(0, 1, 1, 1, 1, 1, 1, 0, 0, 0, 1, 1)

        val actual = Lesson214289().solution(temperature, t1, t2, a, b, onboard)

        actual shouldBe 60
    }
})
