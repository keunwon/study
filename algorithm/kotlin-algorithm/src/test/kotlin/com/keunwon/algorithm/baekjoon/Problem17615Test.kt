package com.keunwon.algorithm.baekjoon

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class Problem17615Test : StringSpec({
    "case-1" {
        val str = "RBBBRBRRR"
        val actual = Problem17615().solution(str)
        actual shouldBe 2
    }

    "case-2" {
        val str = "BBRBBBBR"
        val actual = Problem17615().solution(str)
        actual shouldBe 1
    }
})
