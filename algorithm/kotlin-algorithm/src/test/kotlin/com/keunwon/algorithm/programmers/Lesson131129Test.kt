package com.keunwon.algorithm.programmers

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class Lesson131129Test : StringSpec({
    "case-1" {
        val target = 21
        val actual = Lesson131129().solution(target)
        actual shouldBe intArrayOf(1, 0)
    }

    "case-2" {
        val target = 58
        val actual = Lesson131129().solution(target)
        actual shouldBe intArrayOf(2, 2)
    }
})
