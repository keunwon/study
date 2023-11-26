package com.keunwon.algorithm.programmers

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

internal class Lessons42894Test : StringSpec({
    "기본예제" {
        val board = arrayOf(
            intArrayOf(0, 0, 0, 0, 0, 0, 0, 0, 0, 0),
            intArrayOf(0, 0, 0, 0, 0, 0, 0, 0, 0, 0),
            intArrayOf(0, 0, 0, 0, 0, 0, 0, 0, 0, 0),
            intArrayOf(0, 0, 0, 0, 0, 0, 0, 0, 0, 0),
            intArrayOf(0, 0, 0, 0, 0, 0, 4, 0, 0, 0),
            intArrayOf(0, 0, 0, 0, 0, 4, 4, 0, 0, 0),
            intArrayOf(0, 0, 0, 0, 3, 0, 4, 0, 0, 0),
            intArrayOf(0, 0, 0, 2, 3, 0, 0, 0, 5, 5),
            intArrayOf(1, 2, 2, 2, 3, 3, 0, 0, 0, 5),
            intArrayOf(1, 1, 1, 0, 0, 0, 0, 0, 0, 5)
        )

        val actual = Lessons42894().solution(board)

        actual shouldBe 2
    }
})
