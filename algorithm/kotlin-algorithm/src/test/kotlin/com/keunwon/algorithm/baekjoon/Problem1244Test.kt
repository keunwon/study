package com.keunwon.algorithm.baekjoon

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class Problem1244Test : StringSpec({
    "case-1" {
        val switches = intArrayOf(0, 1, 0, 1, 0, 0, 0, 1)
        val students = arrayOf(
            intArrayOf(1, 3),
            intArrayOf(2, 3),
        )

        val actual = Problem1244().solution(switches, students)

        actual shouldBe intArrayOf(1, 0, 0, 0, 1, 1, 0, 1)
    }
})
