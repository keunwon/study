package com.keunwon.algorithm.programmers

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

internal class Lessons86053Test : StringSpec({
    "case_01" {
        val a = 10
        val b = 10
        val g = intArrayOf(100)
        val s = intArrayOf(100)
        val w = intArrayOf(7)
        val t = intArrayOf(10)

        val actual = Lessons86053().solution(a, b, g, s, w, t)

        actual shouldBe 50
    }

    "case_02" {
        val a = 90
        val b = 500
        val g = intArrayOf(70, 70, 0)
        val s = intArrayOf(0, 0, 500)
        val w = intArrayOf(100, 100, 2)
        val t = intArrayOf(4, 8, 1)

        val actual = Lessons86053().solution(a, b, g, s, w, t)

        actual shouldBe 499
    }
})
