package algorithm.baekjoon

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class Problem15686Test : StringSpec({
    "case-1" {
        val m = 3
        val board = arrayOf(
            intArrayOf(0, 0, 1, 0, 0),
            intArrayOf(0, 0, 2, 0, 1),
            intArrayOf(0, 1, 2, 0, 0),
            intArrayOf(0, 0, 1, 0, 0),
            intArrayOf(0, 0, 0, 0, 2),
        )

        val actual = Problem15686().solution(m, board)

        actual shouldBe 5
    }

    "case-2" {
        val m = 2
        val board = arrayOf(
            intArrayOf(0, 2, 0, 1, 0),
            intArrayOf(1, 0, 1, 0, 0),
            intArrayOf(0, 0, 0, 0, 0),
            intArrayOf(2, 0, 0, 1, 1),
            intArrayOf(2, 2, 0, 1, 2),
        )

        val actual = Problem15686().solution(m, board)

        actual shouldBe 10
    }

    "case-3" {
        val m = 1
        val board = arrayOf(
            intArrayOf(1, 2, 0, 0, 0),
            intArrayOf(1, 2, 0, 0, 0),
            intArrayOf(1, 2, 0, 0, 0),
            intArrayOf(1, 2, 0, 0, 0),
            intArrayOf(1, 2, 0, 0, 0),
        )

        val actual = Problem15686().solution(m, board)

        actual shouldBe 11
    }

    "case-4" {
        val m = 1
        val board = arrayOf(
            intArrayOf(1, 2, 0, 2, 1),
            intArrayOf(1, 2, 0, 2, 1),
            intArrayOf(1, 2, 0, 2, 1),
            intArrayOf(1, 2, 0, 2, 1),
            intArrayOf(1, 2, 0, 2, 1),
        )

        val actual = Problem15686().solution(m, board)

        actual shouldBe 32
    }
})
