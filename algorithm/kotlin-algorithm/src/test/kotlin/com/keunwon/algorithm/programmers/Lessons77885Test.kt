package com.keunwon.algorithm.programmers

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

internal class Lessons77885Test : StringSpec({
    "case_01" {
        val numbers = longArrayOf(2L, 7L)

        val actual = Lessons77885().solution(numbers)

        actual shouldBe longArrayOf(3, 11)
    }
})
