package com.keunwon.algorithm.programmers

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

internal class Lessons131704Test : StringSpec({
    "case_01" {
        val order = intArrayOf(4, 3, 1, 2, 5)
        val actual = Lessons131704().solution(order)
        actual shouldBe 2
    }

    "case_02" {
        val order = intArrayOf(5, 4, 3, 2, 1)
        val actual = Lessons131704().solution(order)
        actual shouldBe 5
    }
})
