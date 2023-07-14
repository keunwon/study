package com.keunwon.algorithm.baekjoon

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

internal class Problem19941Test : StringSpec({
    "case_01" {
        val k = 1
        val food = "HHPHPPHHPPHPPPHPHPHP".toCharArray()

        val actual = Problem19941().solution(k, food)

        actual shouldBe 8
    }

    "case_02" {
        val k = 2
        val food = "HHHHHPPPPPHPHPHPHHHP".toCharArray()

        val actual = Problem19941().solution(k, food)

        actual shouldBe 7
    }
})
