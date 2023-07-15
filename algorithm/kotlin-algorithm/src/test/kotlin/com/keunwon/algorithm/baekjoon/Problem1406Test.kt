package com.keunwon.algorithm.baekjoon

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

internal class Problem1406Test : StringSpec({
    "case_01" {
        val init = "abcd"
        val command = arrayOf(
            'P' to 'x',
            'L' to ' ',
            'P' to 'y',
        )

        val actual = Problem1406().solution(init, command)

        actual shouldBe "abcdyx"
    }

    "case_02" {
        val init = "abc"
        val command = arrayOf(
            'L' to ' ',
            'L' to ' ',
            'L' to ' ',
            'L' to ' ',
            'L' to ' ',
            'P' to 'x',
            'L' to ' ',
            'B' to ' ',
            'P' to 'y',
        )

        val actual = Problem1406().solution(init, command)

        actual shouldBe "yxabc"
    }

    "case_03" {
        val init = "dmih"
        val command = arrayOf(
            'B' to ' ',
            'B' to ' ',
            'P' to 'x',
            'L' to ' ',
            'B' to ' ',
            'B' to ' ',
            'B' to ' ',
            'P' to 'y',
            'D' to ' ',
            'D' to ' ',
            'P' to 'z',
        )

        val actual = Problem1406().solution(init, command)

        actual shouldBe "yxz"
    }
})
