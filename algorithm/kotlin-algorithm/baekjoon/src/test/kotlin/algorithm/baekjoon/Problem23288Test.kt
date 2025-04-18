package algorithm.baekjoon

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class Problem23288Test : StringSpec({
    "case-1" {
        val k = 1
        val board = arrayOf(
            intArrayOf(4, 1, 2, 3, 3),
            intArrayOf(6, 1, 1, 3, 3),
            intArrayOf(5, 6, 1, 3, 2),
            intArrayOf(5, 5, 6, 5, 5),
        )

        val actual = Problem23288().solution(k, board)

        actual shouldBe 4
    }

    "case-2" {
        val k = 2
        val board = arrayOf(
            intArrayOf(4, 1, 2, 3, 3),
            intArrayOf(6, 1, 1, 3, 3),
            intArrayOf(5, 6, 1, 3, 2),
            intArrayOf(5, 5, 6, 5, 5),
        )

        val actual = Problem23288().solution(k, board)

        actual shouldBe 8
    }

    "case-3" {
        val k = 3
        val board = arrayOf(
            intArrayOf(4, 1, 2, 3, 3),
            intArrayOf(6, 1, 1, 3, 3),
            intArrayOf(5, 6, 1, 3, 2),
            intArrayOf(5, 5, 6, 5, 5),
        )

        val actual = Problem23288().solution(k, board)

        actual shouldBe 14
    }

    "case-4" {
        val k = 4
        val board = arrayOf(
            intArrayOf(4, 1, 2, 3, 3),
            intArrayOf(6, 1, 1, 3, 3),
            intArrayOf(5, 6, 1, 3, 2),
            intArrayOf(5, 5, 6, 5, 5),
        )

        val actual = Problem23288().solution(k, board)

        actual shouldBe 18
    }

    "case-5" {
        val k = 5
        val board = arrayOf(
            intArrayOf(4, 1, 2, 3, 3),
            intArrayOf(6, 1, 1, 3, 3),
            intArrayOf(5, 6, 1, 3, 2),
            intArrayOf(5, 5, 6, 5, 5),
        )

        val actual = Problem23288().solution(k, board)

        actual shouldBe 24
    }

    "case-6" {
        val k = 6
        val board = arrayOf(
            intArrayOf(4, 1, 2, 3, 3),
            intArrayOf(6, 1, 1, 3, 3),
            intArrayOf(5, 6, 1, 3, 2),
            intArrayOf(5, 5, 6, 5, 5),
        )

        val actual = Problem23288().solution(k, board)

        actual shouldBe 28
    }

    "case-7" {
        val k = 7
        val board = arrayOf(
            intArrayOf(4, 1, 2, 3, 3),
            intArrayOf(6, 1, 1, 3, 3),
            intArrayOf(5, 6, 1, 3, 2),
            intArrayOf(5, 5, 6, 5, 5),
        )

        val actual = Problem23288().solution(k, board)

        actual shouldBe 43
    }

    "case-8" {
        val k = 1000
        val board = arrayOf(
            intArrayOf(4, 1, 2, 3, 3),
            intArrayOf(6, 1, 1, 3, 3),
            intArrayOf(5, 6, 1, 3, 2),
            intArrayOf(5, 5, 6, 5, 5),
        )

        val actual = Problem23288().solution(k, board)

        actual shouldBe 3901
    }
})
