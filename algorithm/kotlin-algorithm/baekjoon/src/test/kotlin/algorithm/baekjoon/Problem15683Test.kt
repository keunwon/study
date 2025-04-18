package algorithm.baekjoon

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class Problem15683Test : StringSpec({
    "case-1" {
        val board = arrayOf(
            intArrayOf(0, 0, 0, 0, 0, 0),
            intArrayOf(0, 0, 0, 0, 0, 0),
            intArrayOf(0, 0, 1, 0, 6, 0),
            intArrayOf(0, 0, 0, 0, 0, 0),
        )
        val actual = Problem15683().solution(board)
        actual shouldBe 20
    }

    "case-2" {
        val board = arrayOf(
            intArrayOf(0, 0, 0, 0, 0, 0),
            intArrayOf(0, 2, 0, 0, 0, 0),
            intArrayOf(0, 0, 0, 0, 6, 0),
            intArrayOf(0, 6, 0, 0, 2, 0),
            intArrayOf(0, 0, 0, 0, 0, 0),
            intArrayOf(0, 0, 0, 0, 0, 5),
        )
        val actual = Problem15683().solution(board)
        actual shouldBe 15
    }

    "case-3" {
        val board = arrayOf(
            intArrayOf(1, 0, 0, 0, 0, 0),
            intArrayOf(0, 1, 0, 0, 0, 0),
            intArrayOf(0, 0, 1, 0, 0, 0),
            intArrayOf(0, 0, 0, 1, 0, 0),
            intArrayOf(0, 0, 0, 0, 1, 0),
            intArrayOf(0, 0, 0, 0, 0, 1),
        )
        val actual = Problem15683().solution(board)
        actual shouldBe 6
    }

    "case-4" {
        val board = arrayOf(
            intArrayOf(1, 0, 0, 0, 0, 0),
            intArrayOf(0, 1, 0, 0, 0, 0),
            intArrayOf(0, 0, 1, 5, 0, 0),
            intArrayOf(0, 0, 5, 1, 0, 0),
            intArrayOf(0, 0, 0, 0, 1, 0),
            intArrayOf(0, 0, 0, 0, 0, 1),
        )
        val actual = Problem15683().solution(board)
        actual shouldBe 2
    }

    "case-5" {
        val board = arrayOf(intArrayOf(0, 1, 2, 3, 4, 5, 6))
        val actual = Problem15683().solution(board)
        actual shouldBe 0
    }

    "case-6" {
        val board = arrayOf(
            intArrayOf(4, 0, 0, 0, 0, 0, 0),
            intArrayOf(0, 0, 0, 2, 0, 0, 0),
            intArrayOf(0, 0, 0, 0, 0, 0, 4),
        )
        val actual = Problem15683().solution(board)
        actual shouldBe 0
    }
})
