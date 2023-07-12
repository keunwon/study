package com.keunwon.algorithm.programmers

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

internal class Lessons131130Test : StringSpec({
    "case" {
        val cards = intArrayOf(8, 6, 3, 7, 2, 5, 1, 4)
        val actual = Lessons131130().solution(cards)
        actual shouldBe 12
    }
})
