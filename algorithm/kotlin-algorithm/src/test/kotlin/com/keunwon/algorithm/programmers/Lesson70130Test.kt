package com.keunwon.algorithm.programmers

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class Lesson70130Test : StringSpec({
    "case-1" {
        val a = intArrayOf(0)
        val actual = Lesson70130().solution(a)
        actual shouldBe 0
    }

    "case-2" {
        val a = intArrayOf(5, 2, 3, 3, 5, 3)
        val actual = Lesson70130().solution(a)
        actual shouldBe 4
    }

    "case-3" {
        val a = intArrayOf(0, 3, 3, 0, 7, 2, 0, 2, 2, 0)
        val actual = Lesson70130().solution(a)
        actual shouldBe 8
    }
})