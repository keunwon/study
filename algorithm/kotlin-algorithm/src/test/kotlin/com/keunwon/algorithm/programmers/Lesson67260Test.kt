package com.keunwon.algorithm.programmers

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class Lesson67260Test : StringSpec({
    "case-1" {
        val n = 9
        val path = arrayOf(
            intArrayOf(0, 1),
            intArrayOf(0, 3),
            intArrayOf(0, 7),
            intArrayOf(8, 1),
            intArrayOf(3, 6),
            intArrayOf(1, 2),
            intArrayOf(4, 7),
            intArrayOf(7, 5)
        )
        val order = arrayOf(
            intArrayOf(8, 5),
            intArrayOf(6, 7),
            intArrayOf(4, 1)
        )

        val actual = Lesson67260().solution(n, path, order)

        actual shouldBe true
    }

    "case-2" {
        val n = 9
        val path = arrayOf(
            intArrayOf(8, 1),
            intArrayOf(0, 1),
            intArrayOf(1, 2),
            intArrayOf(0, 7),
            intArrayOf(4, 7),
            intArrayOf(0, 3),
            intArrayOf(7, 5),
            intArrayOf(3, 6)
        )
        val order = arrayOf(
            intArrayOf(4, 1),
            intArrayOf(5, 2)
        )

        val actual = Lesson67260().solution(n, path, order)

        actual shouldBe true
    }

    "case-3" {
        val n = 9
        val path = arrayOf(
            intArrayOf(0, 1),
            intArrayOf(0, 3),
            intArrayOf(0, 7),
            intArrayOf(8, 1),
            intArrayOf(3, 6),
            intArrayOf(1, 2),
            intArrayOf(4, 7),
            intArrayOf(7, 5)
        )
        val order = arrayOf(
            intArrayOf(4, 1),
            intArrayOf(8, 7),
            intArrayOf(6, 5)
        )

        val actual = Lesson67260().solution(n, path, order)

        actual shouldBe false
    }
})
