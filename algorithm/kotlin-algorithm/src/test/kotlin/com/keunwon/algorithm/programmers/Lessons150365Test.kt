package com.keunwon.algorithm.programmers

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

internal class Lessons150365Test : StringSpec({
    "case_01" {
        val (n, m, x, y) = intArrayOf(3, 4, 2, 3)
        val (r, c, k) = intArrayOf(3, 1, 5)

        val actual = Lessons150365().solution(n, m, x, y, r, c, k)

        actual shouldBe "dllrl"
    }

    "case_02" {
        val (n, m, x, y) = intArrayOf(2, 2, 1, 1)
        val (r, c, k) = intArrayOf(2, 2, 2)

        val actual = Lessons150365().solution(n, m, x, y, r, c, k)

        actual shouldBe "dr"
    }

    "case_03" {
        val (n, m, x, y) = intArrayOf(3, 3, 1, 2)
        val (r, c, k) = intArrayOf(3, 3, 4)

        val actual = Lessons150365().solution(n, m, x, y, r, c, k)

        actual shouldBe "impossible"
    }
})
