package com.keunwon.algorithm.programmers

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class Lesson42586Test : StringSpec({
    "case-1" {
        val progress = intArrayOf(93, 30, 55)
        val speeds = intArrayOf(1, 30, 5)

        val actual = Lesson42586().solution(progress, speeds)

        actual shouldBe intArrayOf(2, 1)
    }

    "case-2" {
        val progress = intArrayOf(95, 90, 99, 99, 80, 99)
        val speeds = intArrayOf(1, 1, 1, 1, 1, 1)

        val actual = Lesson42586().solution(progress, speeds)

        actual shouldBe intArrayOf(1, 3, 2)
    }
})
