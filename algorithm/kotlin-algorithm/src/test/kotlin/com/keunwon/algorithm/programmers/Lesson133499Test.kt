package com.keunwon.algorithm.programmers

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class Lesson133499Test : StringSpec({
    "case-1" {
        val babbling = arrayOf("aya", "yee", "u", "maa")
        val actual = Lesson133499().solution(babbling)
        actual shouldBe 1
    }

    "case-2" {
        val babbling = arrayOf("ayaye", "uuu", "yeye", "yemawoo", "ayaayaa")
        val actual = Lesson133499().solution(babbling)
        actual shouldBe 2
    }
})
