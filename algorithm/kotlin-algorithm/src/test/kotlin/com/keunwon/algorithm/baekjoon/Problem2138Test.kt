package com.keunwon.algorithm.baekjoon

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

internal class Problem2138Test : StringSpec({
    "case_01" {
        val init = "000"
        val dest = "010"

        val acutla = Problem2138().solution(init, dest)

        acutla shouldBe 3
    }
})
