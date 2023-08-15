package com.keunwon.algorithm.baekjoon

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

internal class Problem13397Test : StringSpec({
    "case_01" {
        val m = 3
        val arr = intArrayOf(1, 5, 4, 6, 2, 1, 3, 7)

        val actual = Problem13397().solution(m, arr)

        actual shouldBe 5
    }

    "case_02" {
        val m = 2
        val arr = intArrayOf(1, 1, 1, 1)

        val actual = Problem13397().solution(m, arr)

        actual shouldBe 0
    }

    "case_03" {
        val m = 4
        val arr = intArrayOf(1, 2, 3, 1, 2, 3, 1)

        val actual = Problem13397().solution(m, arr)

        actual shouldBe 2
    }

    "case_04" {
        val m = 1
        val arr = intArrayOf(1, 100, 99, 2, 3)

        val actual = Problem13397().solution(m, arr)

        actual shouldBe 99
    }

    "case_05" {
        val m = 2
        val arr = intArrayOf(1, 100, 99, 2, 3)

        val actual = Problem13397().solution(m, arr)

        actual shouldBe 98
    }

    "case_06" {
        val m = 3
        val arr = intArrayOf(1, 100, 99, 2, 3)

        val actual = Problem13397().solution(m, arr)
       
        actual shouldBe 1
    }
})
