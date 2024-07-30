package com.keunwon.algorithm.programmers

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class Lesson42840Test : StringSpec({
    "case-1" {
        val answers = intArrayOf(1, 2, 3, 4, 5)
        val actual = Lesson42840().solution(answers)
        actual shouldBe intArrayOf(1)
    }

    "case-2" {
        val answers = intArrayOf(1, 3, 2, 4, 2)
        val actual = Lesson42840().solution(answers)
        actual shouldBe intArrayOf(1, 2, 3)
    }
})
