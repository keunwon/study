package com.keunwon.algorithm.baekjoon

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class Problem1943Test : StringSpec({
    "case-1" {
        val coins = mapOf(
            500 to 1,
            50 to 1,
        )
        val actual = Problem1943().solution(coins)
        actual shouldBe 0
    }

    "case-2" {
        val coins = mapOf(
            100 to 2,
            50 to 1,
            10 to 5,
        )
        val actual = Problem1943().solution(coins)
        actual shouldBe 1
    }

    "case-3" {
        val coins = mapOf(
            1 to 1,
            2 to 1,
            3 to 1,
        )
        val actual = Problem1943().solution(coins)
        actual shouldBe 1
    }
})
