package com.keunwon.algorithm.programmers

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class Lesson77886Test : StringSpec({
    "case-1" {
        val s = arrayOf("1110", "100111100", "0111111010")
        val actual = Lesson77886().solution(s)
        actual shouldBe arrayOf("1101", "100110110", "0110110111")
    }

    "case-2" {
        val sb = StringBuilder("110")
        println(sb[0] == '1')
        println(sb[1])
        println(sb[2])
    }
})
