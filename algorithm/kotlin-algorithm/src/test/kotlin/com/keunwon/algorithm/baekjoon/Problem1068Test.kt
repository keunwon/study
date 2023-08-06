package com.keunwon.algorithm.baekjoon

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

internal class Problem1068Test : StringSpec({
    "case_01" {
        val arr = intArrayOf(-1, 0, 0, 1, 1)
        val delete = 2

        val actual = Problem1068().solution(arr, delete)

        actual shouldBe 2
    }

    "case_02" {
        val arr = intArrayOf(-1, 0, 0, 1, 1)
        val delete = 1

        val actual = Problem1068().solution(arr, delete)

        actual shouldBe 1
    }

    "case_03" {
        val arr = intArrayOf(-1, 0, 0, 1, 1)
        val delete = 0

        val actual = Problem1068().solution(arr, delete)

        actual shouldBe 0
    }

    "case_04" {
        val arr = intArrayOf(-1, 0, 0, 2, 2, 4, 4, 6, 6)
        val delete = 4

        val actual = Problem1068().solution(arr, delete)

        actual shouldBe 2
    }
})
