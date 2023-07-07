package com.keunwon.algorithm.programmers

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

internal class Lessons155652Test : StringSpec({
    "case_01" {
        val s = "aukks"
        val skip = "wbqd"
        val index = 5

        val actual = Lessons155652().solution(s, skip, index)

        actual shouldBe "happy"
    }
})
