package com.keunwon.algorithm.programmers

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class Lesson159993Test : StringSpec({
    "case-1" {
        val map = arrayOf("SOOOL", "XXXXO", "OOOOO", "OXXXX", "OOOOE")
        val actual = Lesson159993().solution(map)
        actual shouldBe 16
    }

    "case-2" {
        val map = arrayOf("LOOXS", "OOOOX", "OOOOO", "OOOOO", "EOOOO")
        val actual = Lesson159993().solution(map)
        actual shouldBe -1
    }
})