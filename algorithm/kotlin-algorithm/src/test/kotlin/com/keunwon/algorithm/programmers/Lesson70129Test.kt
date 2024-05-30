package com.keunwon.algorithm.programmers

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class Lesson70129Test : StringSpec({
    "case-1" {
        val s = "110010101001"
        val actual = Lesson70129().solution(s)
        actual shouldBe intArrayOf(3, 8)
    }

    "case-2" {
        val s = "01110"
        val actual = Lesson70129().solution(s)
        actual shouldBe intArrayOf(3, 3)
    }

    "case-3" {
        val s = "1111111"
        val actual = Lesson70129().solution(s)
        actual shouldBe intArrayOf(4, 1)
    }
})
