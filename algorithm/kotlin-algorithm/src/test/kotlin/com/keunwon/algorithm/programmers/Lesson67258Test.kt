package com.keunwon.algorithm.programmers

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class Lesson67258Test : StringSpec({
    "case-1" {
        val gems = arrayOf("DIA", "RUBY", "RUBY", "DIA", "DIA", "EMERALD", "SAPPHIRE", "DIA")
        val actual = Lesson67258().solution(gems)
        actual shouldBe intArrayOf(3, 7)
    }

    "case-2" {
        val gems = arrayOf("AA", "AB", "AC", "AA", "AC")
        val actual = Lesson67258().solution(gems)
        actual shouldBe intArrayOf(1, 3)
    }

    "case-3" {
        val gems = arrayOf("XYZ", "XYZ", "XYZ")
        val actual = Lesson67258().solution(gems)
        actual shouldBe intArrayOf(1, 1)
    }

    "case-4" {
        val gems = arrayOf("ZZZ", "YYY", "NNNN", "YYY", "BBB")
        val actual = Lesson67258().solution(gems)
        actual shouldBe intArrayOf(1, 5)
    }
})