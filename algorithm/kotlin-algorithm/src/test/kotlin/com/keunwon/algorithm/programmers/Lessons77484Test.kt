package com.keunwon.algorithm.programmers

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

internal class Lessons77484Test : StringSpec({
    "case_01" {
        val lottos = intArrayOf(44, 1, 0, 0, 31, 25)
        val win_nums = intArrayOf(31, 10, 45, 1, 6, 19)

        val actual = Lessons77484().solution(lottos, win_nums)

        actual shouldBe intArrayOf(3, 5)
    }

    "case_02" {
        val lottos = intArrayOf(0, 0, 0, 0, 0, 0)
        val win_nums = intArrayOf(38, 19, 20, 40, 15, 25)

        val actual = Lessons77484().solution(lottos, win_nums)

        actual shouldBe intArrayOf(1, 6)
    }

    "case_03" {
        val lottos = intArrayOf(45, 4, 35, 20, 3, 9)
        val win_nums = intArrayOf(20, 9, 3, 45, 4, 35)

        val actual = Lessons77484().solution(lottos, win_nums)

        actual shouldBe intArrayOf(1, 1)
    }
})
