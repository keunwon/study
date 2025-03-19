package com.keunwon.algorithm.baekjoon

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class Problem11501Test : StringSpec({
    "case-1" {
        val stocks = listOf(10L, 7L, 6L)
        val actual = Problem11501().solution(stocks)
        actual shouldBe 0L
    }

    "case-2" {
        val stocks = listOf(3L, 5L, 9)
        val actual = Problem11501().solution(stocks)
        actual shouldBe 10
    }

    "case-3" {
        val stocks = listOf(1L, 1L, 3L, 1L, 2L)
        val actual = Problem11501().solution(stocks)
        actual shouldBe 5
    }
})
