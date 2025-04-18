package algorithm.baekjoon

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class Problem16236Test : StringSpec({
    "case-1" {
        val board = arrayOf(
            intArrayOf(0, 0, 0),
            intArrayOf(0, 0, 0),
            intArrayOf(0, 9, 0),
        )
        val actual = Problem16236().solution(board)
        actual shouldBe 0
    }

    "case-2" {
        val board = arrayOf(
            intArrayOf(0, 0, 1),
            intArrayOf(0, 0, 0),
            intArrayOf(0, 9, 0),
        )
        val actual = Problem16236().solution(board)
        actual shouldBe 3
    }

    "case-3" {
        val board = arrayOf(
            intArrayOf(4, 3, 2, 1),
            intArrayOf(0, 0, 0, 0),
            intArrayOf(0, 0, 9, 0),
            intArrayOf(1, 2, 3, 4),
        )
        val actual = Problem16236().solution(board)
        actual shouldBe 14
    }

    "case-4" {
        val board = arrayOf(
            intArrayOf(5, 4, 3, 2, 3, 4),
            intArrayOf(4, 3, 2, 3, 4, 5),
            intArrayOf(3, 2, 9, 5, 6, 6),
            intArrayOf(2, 1, 2, 3, 4, 5),
            intArrayOf(3, 2, 1, 6, 5, 4),
            intArrayOf(6, 6, 6, 6, 6, 6),
        )
        val actual = Problem16236().solution(board)
        actual shouldBe 60
    }

    "case-5" {
        val board = arrayOf(
            intArrayOf(6, 0, 6, 0, 6, 1),
            intArrayOf(0, 0, 0, 0, 0, 2),
            intArrayOf(2, 3, 4, 5, 6, 6),
            intArrayOf(0, 0, 0, 0, 0, 2),
            intArrayOf(0, 2, 0, 0, 0, 0),
            intArrayOf(3, 9, 3, 0, 0, 1),
        )
        val actual = Problem16236().solution(board)
        actual shouldBe 48
    }

    "case-6" {
        val board = arrayOf(
            intArrayOf(1, 1, 1, 1, 1, 1),
            intArrayOf(2, 2, 6, 2, 2, 3),
            intArrayOf(2, 2, 5, 2, 2, 3),
            intArrayOf(2, 2, 2, 4, 6, 3),
            intArrayOf(0, 0, 0, 0, 0, 6),
            intArrayOf(0, 0, 0, 0, 0, 9),
        )
        val actual = Problem16236().solution(board)
        actual shouldBe 39
    }
})
