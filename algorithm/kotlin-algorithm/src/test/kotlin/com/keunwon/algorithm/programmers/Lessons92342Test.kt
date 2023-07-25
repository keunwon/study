package com.keunwon.algorithm.programmers

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

internal class Lessons92342Test : StringSpec({
    "case_01" {
        val n = 5
        val info = intArrayOf(2, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0)

        val actual = Lessons92342().solution(n, info)

        actual shouldBe intArrayOf(0, 2, 2, 0, 1, 0, 0, 0, 0, 0, 0)
    }

    "case_02" {
        val n = 1
        val info = intArrayOf(1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0)

        val actual = Lessons92342().solution(n, info)

        actual shouldBe intArrayOf(-1)
    }

    "case_03" {
        val n = 9
        val info = intArrayOf(0, 0, 1, 2, 0, 1, 1, 1, 1, 1, 1)

        val actual = Lessons92342().solution(n, info)

        actual shouldBe intArrayOf(1, 1, 2, 0, 1, 2, 2, 0, 0, 0, 0)
    }

    "case_04" {
        val n = 10
        val log = intArrayOf(0, 0, 0, 0, 0, 0, 0, 0, 3, 4, 3)

        val actual = Lessons92342().solution(n, log)

        actual shouldBe intArrayOf(1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 2)
    }
})
