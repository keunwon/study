package com.keunwon.algorithm.programmers

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

internal class Lessons70130Test : StringSpec({
    "case_01" {
        val a = intArrayOf(0)
        val actual = Lessons70130().solution(a)
        actual shouldBe 0
    }

    "case_02" {
        val a = intArrayOf(5, 2, 3, 3, 5, 3)
        val actual = Lessons70130().solution(a)
        actual shouldBe 4
    }

    "case_03" {
        val a = intArrayOf(0, 3, 3, 0, 7, 2, 0, 2, 2, 0)
        val actual = Lessons70130().solution(a)
        actual shouldBe 8
    }
})
