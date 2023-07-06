package com.keunwon.algorithm.programmers

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

internal class Lessons133499Test : StringSpec({
    "case_01" {
        val babbling = arrayOf("aya", "yee", "u", "maa")

        val actual = Lessons133499().solution(babbling)

        actual shouldBe 1
    }

    "case_02" {
        val babbling = arrayOf("ayaye", "uuu", "yeye", "yemawoo", "ayaayaa")

        val actual = Lessons133499().solution(babbling)

        actual shouldBe 2
    }
})
