package com.keunwon.algorithm.baekjoon

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class Problem20125Test : StringSpec({
    "case-1" {
        val board = arrayOf(
            "_____",
            "__*__",
            "_***_",
            "__*__",
            "_*_*_",
        )

        val actual = Problem20125().solution(board)

        actual shouldBe arrayOf(
            intArrayOf(3, 3),
            intArrayOf(1, 1, 1, 1, 1),
        )
    }

    "case-2" {
        val board = arrayOf(
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

        val actual = Problem20125().solution(board)

        actual shouldBe arrayOf(
            intArrayOf(3, 6),
            intArrayOf(3, 2, 3, 4, 1)
        )
    }

    "case-3" {
        val board = arrayOf(
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

        val actual = Problem20125().solution(board)

        actual shouldBe arrayOf(
            intArrayOf(2, 5),
            intArrayOf(4, 4, 3, 4, 4),
        )
    }
})
