package com.keunwon.algorithm.baekjoon

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class Problem19237Test : StringSpec({
    "case-1" {
        val k = 4
        val map = arrayOf(
            intArrayOf(0, 0, 0, 0, 3),
            intArrayOf(0, 2, 0, 0, 0),
            intArrayOf(1, 0, 0, 0, 4),
            intArrayOf(0, 0, 0, 0, 0),
            intArrayOf(0, 0, 0, 0, 0),
        )
        val sharkDirs = intArrayOf(4, 4, 3, 1)
        val priorityDirs = arrayOf(
            arrayOf(
                intArrayOf(2, 3, 1, 4),
                intArrayOf(4, 1, 2, 3),
                intArrayOf(3, 4, 2, 1),
                intArrayOf(4, 3, 1, 2),
            ),
            arrayOf(
                intArrayOf(2, 4, 3, 1),
                intArrayOf(2, 1, 3, 4),
                intArrayOf(3, 4, 1, 2),
                intArrayOf(4, 1, 2, 3),
            ),
            arrayOf(
                intArrayOf(4, 3, 2, 1),
                intArrayOf(1, 4, 3, 2),
                intArrayOf(1, 3, 2, 4),
                intArrayOf(3, 2, 1, 4),
            ),
            arrayOf(
                intArrayOf(3, 4, 1, 2),
                intArrayOf(3, 2, 4, 1),
                intArrayOf(1, 4, 2, 3),
                intArrayOf(1, 4, 2, 3),
            ),
        )

        val actual = Problem19237().solution(k, map, sharkDirs, priorityDirs)

        actual shouldBe 14
    }

    "case-2" {
        val k = 6
        val map = arrayOf(
            intArrayOf(1, 0, 0, 0),
            intArrayOf(0, 0, 0, 0),
            intArrayOf(0, 0, 0, 0),
            intArrayOf(0, 0, 0, 2),
        )
        val sharkDirs = intArrayOf(4, 3)
        val priorityDirs = arrayOf(
            arrayOf(
                intArrayOf(1, 2, 3, 4),
                intArrayOf(2, 3, 4, 1),
                intArrayOf(3, 4, 1, 2),
                intArrayOf(4, 1, 2, 3),
            ),
            arrayOf(
                intArrayOf(1, 2, 3, 4),
                intArrayOf(2, 3, 4, 1),
                intArrayOf(3, 4, 1, 2),
                intArrayOf(4, 1, 2, 3),
            )
        )

        val actual = Problem19237().solution(k, map, sharkDirs, priorityDirs)

        actual shouldBe 26
    }

    "case-3" {
        val k = 1
        val map = arrayOf(
            intArrayOf(0, 0, 0, 0, 3),
            intArrayOf(0, 2, 0, 0, 0),
            intArrayOf(1, 0, 0, 0, 4),
            intArrayOf(0, 0, 0, 0, 0),
            intArrayOf(0, 0, 0, 0, 0),
        )
        val sharkDirs = intArrayOf(4, 4, 3, 1)
        val priorityDirs = arrayOf(
            arrayOf(
                intArrayOf(2, 3, 1, 4),
                intArrayOf(4, 1, 2, 3),
                intArrayOf(3, 4, 2, 1),
                intArrayOf(4, 3, 1, 2),
            ),
            arrayOf(
                intArrayOf(2, 4, 3, 1),
                intArrayOf(2, 1, 3, 4),
                intArrayOf(3, 4, 1, 2),
                intArrayOf(4, 1, 2, 3),
            ),
            arrayOf(
                intArrayOf(4, 3, 2, 1),
                intArrayOf(1, 4, 3, 2),
                intArrayOf(1, 3, 2, 4),
                intArrayOf(3, 2, 1, 4),
            ),
            arrayOf(
                intArrayOf(3, 4, 1, 2),
                intArrayOf(3, 2, 4, 1),
                intArrayOf(1, 4, 2, 3),
                intArrayOf(1, 4, 2, 3),
            ),
        )

        val actual = Problem19237().solution(k, map, sharkDirs, priorityDirs)

        actual shouldBe -1
    }

    "case-4" {
        val k = 10
        val map = arrayOf(
            intArrayOf(0, 0, 0, 0, 3),
            intArrayOf(0, 0, 0, 0, 0),
            intArrayOf(1, 2, 0, 0, 0),
            intArrayOf(0, 0, 0, 0, 4),
            intArrayOf(0, 0, 0, 0, 0),
        )
        val sharkDirs = intArrayOf(4, 4, 3, 1)
        val priorityDirs = arrayOf(
            arrayOf(
                intArrayOf(2, 3, 1, 4),
                intArrayOf(4, 1, 2, 3),
                intArrayOf(3, 4, 2, 1),
                intArrayOf(4, 3, 1, 2),
            ),
            arrayOf(
                intArrayOf(2, 4, 3, 1),
                intArrayOf(2, 1, 3, 4),
                intArrayOf(3, 4, 1, 2),
                intArrayOf(4, 1, 2, 3),
            ),
            arrayOf(
                intArrayOf(4, 3, 2, 1),
                intArrayOf(1, 4, 3, 2),
                intArrayOf(1, 3, 2, 4),
                intArrayOf(3, 2, 1, 4),
            ),
            arrayOf(
                intArrayOf(3, 4, 1, 2),
                intArrayOf(3, 2, 4, 1),
                intArrayOf(1, 4, 2, 3),
                intArrayOf(1, 4, 2, 3),
            ),
        )

        val actual = Problem19237().solution(k, map, sharkDirs, priorityDirs)

        actual shouldBe -1
    }
})
