package com.keunwon.algorithm.programmers

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class Lesson1837Test : StringSpec({
    "case-1" {
        val n = 7
        val m = 10
        val edge_list = arrayOf(
            intArrayOf(1, 2),
            intArrayOf(1, 3),
            intArrayOf(2, 3),
            intArrayOf(2, 4),
            intArrayOf(3, 4),
            intArrayOf(3, 5),
            intArrayOf(4, 6),
            intArrayOf(5, 6),
            intArrayOf(5, 7),
            intArrayOf(6, 7),
        )
        val k = 6
        val gps_log = intArrayOf(1, 2, 3, 3, 6, 7)

        val actual = Lesson1837().solution(n, m, edge_list, k, gps_log)

        actual shouldBe 1
    }

    "case-2" {
        val n = 7
        val m = 10
        val edge_list = arrayOf(
            intArrayOf(1, 2),
            intArrayOf(1, 3),
            intArrayOf(2, 3),
            intArrayOf(2, 4),
            intArrayOf(3, 4),
            intArrayOf(3, 5),
            intArrayOf(4, 6),
            intArrayOf(5, 6),
            intArrayOf(5, 7),
            intArrayOf(6, 7)
        )
        val k = 6
        val gps_log = intArrayOf(1, 2, 4, 6, 5, 7)

        val actual = Lesson1837().solution(n, m, edge_list, k, gps_log)

        actual shouldBe 0
    }
})
