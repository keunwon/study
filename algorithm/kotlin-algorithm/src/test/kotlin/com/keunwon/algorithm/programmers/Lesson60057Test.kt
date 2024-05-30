package com.keunwon.algorithm.programmers

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class Lesson60057Test : StringSpec({
    "case-1" {
        val s = "aabbaccc"
        val actual = Lesson60057().solution(s)
        actual shouldBe 7
    }

    "case-2" {
        val s = "ababcdcdababcdcd"
        val actual = Lesson60057().solution(s)
        actual shouldBe 9
    }

    "case-3" {
        val s = "abcabcdede"
        val actual = Lesson60057().solution(s)
        actual shouldBe 8
    }

    "case-4" {
        val s = "abcabcabcabcdededededede"
        val actual = Lesson60057().solution(s)
        actual shouldBe 14
    }

    "case-5" {
        val s = "xababcdcdababcdcd"
        val actual = Lesson60057().solution(s)
        actual shouldBe 17
    }
})
