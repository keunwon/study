package com.keunwon.algorithm.programmers

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

internal class Lessons64062Test : StringSpec({
    "case" {
        val stones = intArrayOf(2, 4, 5, 3, 2, 1, 4, 2, 5, 1)
        val k = 3

        val actual = Lessons64062().solution(stones, k)

        actual shouldBe 3
    }
})
