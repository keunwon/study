package com.keunwon.algorithm.baekjoon

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

internal class Problem20125Test : StringSpec({
    "case_01" {
        val map = arrayOf(
            "_____",
            "__*__",
            "_***_",
            "__*__",
            "_*_*_",
        )
        val actual = Problem20125().solution(map)
        actual shouldBe arrayOf(
            intArrayOf(3, 3),
            intArrayOf(1, 1, 1, 1, 1),
        )
    }

    "case_02" {
        val map = arrayOf(
            "__________",
            "_____*____",
            "__******__",
            "_____*____",
            "_____*____",
            "_____*____",
            "____*_*___",
            "____*_____",
            "____*_____",
            "____*_____",
        )
        val actual = Problem20125().solution(map)
        actual shouldBe arrayOf(
            intArrayOf(3, 6),
            intArrayOf(3, 2, 3, 4, 1),
        )
    }

    "case_03" {
        val map = arrayOf(
            "____*____",
            "*********",
            "____*____",
            "____*____",
            "____*____",
            "___*_*___",
            "___*_*___",
            "___*_*___",
            "___*_*___",
        )
        val actual = Problem20125().solution(map)
        actual shouldBe arrayOf(
            intArrayOf(2, 5),
            intArrayOf(4, 4, 3, 4, 4),
        )
    }
})
