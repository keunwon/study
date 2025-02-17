package com.keunwon.algorithm.baekjoon

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class Problem19236Test : StringSpec({
    "case-1" {
        val infos = arrayOf(
            intArrayOf(7, 6, 2, 3, 15, 6, 9, 8),
            intArrayOf(3, 1, 1, 8, 14, 7, 10, 1),
            intArrayOf(6, 1, 13, 6, 4, 3, 11, 4),
            intArrayOf(16, 1, 8, 7, 5, 2, 12, 2),
        )
        val actual = Problem19236().solution(infos)
        actual shouldBe 33
    }

    "case-2" {
        val infos = arrayOf(
            intArrayOf(16, 7, 1, 4, 4, 3, 12, 8),
            intArrayOf(14, 7, 7, 6, 3, 4, 10, 2),
            intArrayOf(5, 2, 15, 2, 8, 3, 6, 4),
            intArrayOf(11, 8, 2, 4, 13, 5, 9, 4),
        )
        val actual = Problem19236().solution(infos)
        actual shouldBe 43
    }

    "case-3" {
        val infos = arrayOf(
            intArrayOf(12, 6, 14, 5, 4, 5, 6, 7),
            intArrayOf(15, 1, 11, 7, 3, 7, 7, 5),
            intArrayOf(10, 3, 8, 3, 16, 6, 1, 1),
            intArrayOf(5, 8, 2, 7, 13, 6, 9, 2),
        )
        val actual = Problem19236().solution(infos)
        actual shouldBe 76
    }

    "case-4" {
        val infos = arrayOf(
            intArrayOf(2, 6, 10, 8, 6, 7, 9, 4),
            intArrayOf(1, 7, 16, 6, 4, 2, 5, 8),
            intArrayOf(3, 7, 8, 6, 7, 6, 14, 8),
            intArrayOf(12, 7, 15, 4, 11, 3, 13, 3),
        )
        val actual = Problem19236().solution(infos)
        actual shouldBe 39
    }
})
