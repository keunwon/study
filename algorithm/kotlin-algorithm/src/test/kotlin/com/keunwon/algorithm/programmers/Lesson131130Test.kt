package com.keunwon.algorithm.programmers

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class Lesson131130Test : StringSpec({
    "case-1" {
        val cards = intArrayOf(8, 6, 3, 7, 2, 5, 1, 4)
        val actual = Lesson131130().solution(cards)
        actual shouldBe 12
    }
})