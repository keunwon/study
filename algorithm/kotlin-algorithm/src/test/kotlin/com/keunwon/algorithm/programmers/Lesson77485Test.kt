package com.keunwon.algorithm.programmers

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class Lesson77485Test : StringSpec({
    "case-1" {
        val rows = 6
        val columns = 6
        val queries = arrayOf(
            intArrayOf(2, 2, 5, 4),
            intArrayOf(3, 3, 6, 6),
            intArrayOf(5, 1, 6, 3)
        )

        val actual = Lesson77485().solution(rows, columns, queries)

        actual shouldBe intArrayOf(8, 10, 25)
    }

    "case-2" {
        val rows = 3
        val columns = 3
        val queries = arrayOf(
            intArrayOf(1, 1, 2, 2),
            intArrayOf(1, 2, 2, 3),
            intArrayOf(2, 1, 3, 2),
            intArrayOf(2, 2, 3, 3)
        )

        val actual = Lesson77485().solution(rows, columns, queries)

        actual shouldBe intArrayOf(1, 1, 5, 3)
    }

    "case-3" {
        val rows = 100
        val columns = 97
        val queries = arrayOf(intArrayOf(1, 1, 100, 97))

        val actual = Lesson77485().solution(rows, columns, queries)

        actual shouldBe intArrayOf(1)
    }
})