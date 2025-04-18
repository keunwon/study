package algorithm.baekjoon

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class Problem12100Test : StringSpec({
    "case-1" {
        val board = arrayOf(
            intArrayOf(2, 2, 2),
            intArrayOf(4, 4, 4),
            intArrayOf(8, 8, 8),
        )
        val actual = Problem12100().solution(board)
        actual shouldBe 16
    }

    "case-2" {
        val board = arrayOf(
            intArrayOf(0, 0, 2, 0),
            intArrayOf(0, 0, 0, 0),
            intArrayOf(2, 0, 0, 0),
            intArrayOf(0, 0, 0, 0),
        )
        val actual = Problem12100().solution(board)
        actual shouldBe 4
    }

    "case-3" {
        val board = arrayOf(
            intArrayOf(4, 2, 0, 0),
            intArrayOf(0, 0, 0, 0),
            intArrayOf(0, 0, 0, 0),
            intArrayOf(2, 0, 0, 0),
        )
        val actual = Problem12100().solution(board)
        actual shouldBe 8
    }

    "case-4" {
        val board = arrayOf(
            intArrayOf(2, 0, 2, 8),
            intArrayOf(0, 0, 2, 2),
            intArrayOf(0, 0, 0, 0),
            intArrayOf(0, 0, 0, 0),
        )
        val actual = Problem12100().solution(board)
        actual shouldBe 16
    }
})
