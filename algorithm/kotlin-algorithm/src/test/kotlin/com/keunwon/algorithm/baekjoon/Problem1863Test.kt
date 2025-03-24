package com.keunwon.algorithm.baekjoon

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class Problem1863Test : StringSpec({
    "case-1" {
        val positions = arrayOf(
            1 to 1,
            2 to 2,
            5 to 1,
            6 to 3,
            8 to 1,
            11 to 0,
            15 to 2,
            17 to 3,
            20 to 2,
            22 to 1,
        )
        val actual = Problem1863().solution(positions)
        actual shouldBe 6
    }
})
