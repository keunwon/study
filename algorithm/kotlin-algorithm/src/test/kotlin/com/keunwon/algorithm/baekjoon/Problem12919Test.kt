package com.keunwon.algorithm.baekjoon

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

internal class Problem12919Test : StringSpec({
    "case_01" {
        val s = "A"
        val t = "BABA"

        val actual = Problem12919().solution(s, t)

        actual shouldBe 1
    }

    "case_02" {
        val s = "BAAAAABAA"
        val t = "BAABAAAAAB"

        val actual = Problem12919().solution(s, t)

        actual shouldBe 1
    }

    "case_03" {
        val s = "A"
        val t = "ABBA"

        val actual = Problem12919().solution(s, t)

        actual shouldBe 0
    }
})
