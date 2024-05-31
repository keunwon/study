package com.keunwon.algorithm.programmers

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class Lesson148653Test : StringSpec({
    "case-1" {
        val storey = 16
        val actual = Lesson148653().solution(storey)
        actual shouldBe 6
    }

    "case-2" {
        val storey = 2554
        val actual = Lesson148653().solution(storey)
        actual shouldBe 16
    }
})