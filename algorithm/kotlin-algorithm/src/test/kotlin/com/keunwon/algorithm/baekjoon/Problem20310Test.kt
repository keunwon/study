package com.keunwon.algorithm.baekjoon

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class Problem20310Test : StringSpec({
    "case-1" {
        val str = "1010"
        val actual = Problem20310().solution(str)
        actual shouldBe "01"
    }

    "case-2" {
        val str = "000011"
        val actual = Problem20310().solution(str)
        actual shouldBe "001"
    }
})
