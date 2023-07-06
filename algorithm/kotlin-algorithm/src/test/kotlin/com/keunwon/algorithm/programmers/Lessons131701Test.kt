package com.keunwon.algorithm.programmers

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

internal class Lessons131701Test : StringSpec({
    "case" {
        val elements = intArrayOf(7, 9, 1, 1, 4)
        val actual = Lessons131701().solution(elements)
        actual shouldBe 18
    }
})
