package com.keunwon.algorithm.baekjoon

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

internal class Problem14719Test : StringSpec({
    "case_01" {
        val h = 4
        val w = 4
        val arr = intArrayOf(3, 0, 1, 4)

        val actual = Problem14719().solution(h, w, arr)

        actual shouldBe 5
    }

    "case_02" {
        val h = 4
        val w = 8
        val arr = intArrayOf(3, 1, 2, 3, 4, 1, 1, 2)

        val actual = Problem14719().solution(h, w, arr)

        actual shouldBe 5
    }

    "case_03" {
        val h = 3
        val w = 5
        val arr = intArrayOf(0, 0, 0, 2, 0)

        val actual = Problem14719().solution(h, w, arr)

        actual shouldBe 0
    }
})
