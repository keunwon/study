package com.keunwon.algorithm.programmers

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class Lesson178871Test : StringSpec({
    "case-1" {
        val players = arrayOf("mumu", "soe", "poe", "kai", "mine")
        val callings = arrayOf("kai", "kai", "mine", "mine")

        val actual = Lesson178871().solution(players, callings)

        actual shouldBe arrayOf("mumu", "kai", "mine", "soe", "poe")
    }
})
