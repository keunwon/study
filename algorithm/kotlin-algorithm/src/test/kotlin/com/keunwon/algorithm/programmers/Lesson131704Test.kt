package com.keunwon.algorithm.programmers

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class Lesson131704Test : StringSpec({
    "case-1" {
        val order = intArrayOf(4, 3, 1, 2, 5)
        val actual = Lesson131704().solution(order)
        actual shouldBe 2
    }

    "case-2" {
        val order = intArrayOf(5, 4, 3, 2, 1)
        val actual = Lesson131704().solution(order)
        actual shouldBe 5
    }
})
