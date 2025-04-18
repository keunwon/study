package com.keunwon.algorithm.baekjoon

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class Problem14658Test : StringSpec({
    "case-1" {
        val n = 12
        val m = 10
        val l = 4
        val shootingStarts = arrayOf(
            2 to 4,
            7 to 3,
            3 to 1,
            5 to 6,
            4 to 7,
            12 to 10,
            8 to 6,
        )

        val actual = Problem14658().solution(n, m, l, shootingStarts)

        actual shouldBe 3
    }
})
