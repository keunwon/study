package com.keunwon.algorithm.baekjoon

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

internal class Problem2304Test : StringSpec({
    "case" {
        val arr = arrayOf(
            2 to 4,
            11 to 4,
            15 to 8,
            4 to 6,
            5 to 3,
            8 to 10,
            13 to 6,
        )

        val actual = Problem2304().solution(arr)

        actual shouldBe 98
    }
})
