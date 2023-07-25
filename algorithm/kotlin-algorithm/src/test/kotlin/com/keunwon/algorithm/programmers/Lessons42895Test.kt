package com.keunwon.algorithm.programmers

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

internal class Lessons42895Test : StringSpec({
    "case_01" {
        val n = 5
        val number = 12

        val actual = Lessons42895().solution(n, number)

        actual shouldBe 4
    }

    "case_02" {
        val n = 2
        val number = 11

        val actual = Lessons42895().solution(n, number)

        actual shouldBe 3
    }
})
