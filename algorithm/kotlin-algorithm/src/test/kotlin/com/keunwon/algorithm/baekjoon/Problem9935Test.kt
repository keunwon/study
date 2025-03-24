package com.keunwon.algorithm.baekjoon

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class Problem9935Test : StringSpec({
    "case-1" {
        val str1 = "mirkovC4nizCC44"
        val str2 = "C4"

        val actual = Problem9935().solution(str1, str2)

        actual shouldBe "mirkovniz"
    }

    "case-2" {
        val str1 = "12ab112ab2ab"
        val str2 = "12ab"

        val actual = Problem9935().solution(str1, str2)

        actual shouldBe "FRULA"
    }
})
