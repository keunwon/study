package com.keunwon.algorithm.baekjoon

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class Problem2138KtTest : StringSpec({
    "case-1" {
        val src = "000"
        val dest = "010"

        val actual = Problem2138().solution(src, dest)

        actual shouldBe 3
    }
})
