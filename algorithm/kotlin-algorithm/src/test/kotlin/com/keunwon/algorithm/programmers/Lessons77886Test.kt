package com.keunwon.algorithm.programmers

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

internal class Lessons77886Test : StringSpec({
    "case_01" {
        val s = arrayOf("1110", "100111100", "0111111010")
        val actual = Lessons77886().solution(s)
        actual shouldBe arrayOf("1101", "100110110", "0110110111")
    }
})
