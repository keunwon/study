package com.keunwon.algorithm.programmers

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

internal class Lessons138477Test : StringSpec({
    "case_01" {
        val k = 3
        val score = intArrayOf(10, 100, 20, 150, 1, 100, 200)

        val actual = Lessons138477().solution(k, score)

        actual shouldBe intArrayOf(10, 10, 10, 20, 20, 100, 100)
    }

    "case_02" {
        val k = 4
        val score = intArrayOf(0, 300, 40, 300, 20, 70, 150, 50, 500, 1000)

        val actual = Lessons138477().solution(k, score)

        actual shouldBe intArrayOf(0, 0, 0, 0, 20, 40, 70, 70, 150, 300)
    }
})
