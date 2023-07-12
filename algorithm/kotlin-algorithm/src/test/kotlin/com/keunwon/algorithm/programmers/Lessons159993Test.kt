package com.keunwon.algorithm.programmers

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

internal class Lessons159993Test : StringSpec({
    "case_01" {
        val maps = arrayOf("SOOOL", "XXXXO", "OOOOO", "OXXXX", "OOOOE")
        val actual = Lessons159993().solution(maps)
        actual shouldBe 16
    }

    "case_02" {
        val maps = arrayOf("LOOXS", "OOOOX", "OOOOO", "OOOOO", "EOOOO")
        val actual = Lessons159993().solution(maps)
        actual shouldBe -1
    }
})
