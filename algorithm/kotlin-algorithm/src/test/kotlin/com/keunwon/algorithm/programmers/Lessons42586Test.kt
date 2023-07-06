package com.keunwon.algorithm.programmers

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

internal class Lessons42586Test : StringSpec({
    "case_01" {
        val progress = intArrayOf(93, 30, 55)
        val speeds = intArrayOf(1, 30, 5)

        val actual = Lessons42586().solution(progress, speeds)

        actual shouldBe intArrayOf(2, 1)
    }

    "case_02" {
        val progress = intArrayOf(95, 90, 99, 99, 80, 99)
        val speeds = intArrayOf(1, 1, 1, 1, 1, 1)

        val actual = Lessons42586().solution(progress, speeds)

        actual shouldBe intArrayOf(1, 3, 2)
    }
})
