package com.keunwon.algorithm.programmers

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

internal class Lessons131129Test : StringSpec({
    "case_01" {
        val target = 21
        val actual = Lessons131129().solution(target)
        actual shouldBe intArrayOf(1, 0)
    }

    "case_02" {
        val target = 58
        val actual = Lessons131129().solution(target)
        actual shouldBe intArrayOf(2, 2)
    }
})
