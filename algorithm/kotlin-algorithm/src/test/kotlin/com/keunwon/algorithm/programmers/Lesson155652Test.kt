package com.keunwon.algorithm.programmers

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class Lesson155652Test : StringSpec({
    "case-1" {
        val s = "aukks"
        val skip = "wbqd"
        val index = 5

        val actual = Lesson155652().solution(s, skip, index)

        // hfssc
        actual shouldBe "happy"
    }
})
