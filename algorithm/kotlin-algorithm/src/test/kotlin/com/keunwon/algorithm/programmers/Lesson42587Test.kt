package com.keunwon.algorithm.programmers

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class Lesson42587Test : StringSpec({
    "case-1" {
        val priorities = intArrayOf(2, 1, 3, 2)
        val location = 2

        val actual = Lesson42587().solution(priorities, location)

        actual shouldBe 1
    }

    "case-2" {
        val priorities = intArrayOf(1, 1, 9, 1, 1, 1)
        val location = 0

        val actual = Lesson42587().solution(priorities, location)

        actual shouldBe 5
    }
})
