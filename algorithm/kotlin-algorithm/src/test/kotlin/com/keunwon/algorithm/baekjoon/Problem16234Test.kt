package com.keunwon.algorithm.baekjoon

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class Problem16234Test : StringSpec({
    "case-1" {
        val l = 20
        val r = 50
        val map = arrayOf(
            intArrayOf(50, 30),
            intArrayOf(20, 40),
        )

        val actual = Problem16234().solution(l, r, map)

        actual shouldBe 1
    }

    "case-2" {
        val l = 40
        val r = 50
        val map = arrayOf(
            intArrayOf(50, 30),
            intArrayOf(20, 40),
        )

        val actual = Problem16234().solution(l, r, map)

        actual shouldBe 0
    }

    "case-3" {
        val l = 20
        val r = 50
        val map = arrayOf(
            intArrayOf(50, 30),
            intArrayOf(30, 40),
        )

        val actual = Problem16234().solution(l, r, map)

        actual shouldBe 1
    }

    "case-4" {
        val l = 5
        val r = 10
        val map = arrayOf(
            intArrayOf(10, 15, 20),
            intArrayOf(20, 30, 25),
            intArrayOf(40, 22, 10),
        )

        val actual = Problem16234().solution(l, r, map)

        actual shouldBe 2
    }

    "case-5" {
        val l = 10
        val r = 50
        val map = arrayOf(
            intArrayOf(10, 100, 20, 90),
            intArrayOf(80, 100, 60, 70),
            intArrayOf(70, 20, 30, 40),
            intArrayOf(50, 20, 100, 10),
        )

        val actual = Problem16234().solution(l, r, map)

        actual shouldBe 3
    }
})
