package com.keunwon.algorithm.programmers

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

internal class Lessons161988Test : StringSpec({
    "case_01" {
        val sequence = intArrayOf(2, 3, -6, 1, 3, -1, 2, 4)
        val actual = Lessons161988().solution(sequence)
        actual shouldBe 10
    }
})
