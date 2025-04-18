package algorithm.baekjoon

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class Problem2075Test : StringSpec({
    "case-1" {
        val board = arrayOf(
            intArrayOf(12, 7, 9, 15, 5),
            intArrayOf(13, 8, 11, 19, 6),
            intArrayOf(21, 10, 26, 31, 16),
            intArrayOf(48, 14, 28, 35, 25),
            intArrayOf(52, 20, 32, 41, 49),
        )
        val actual = Problem2075().solution(board)
        actual shouldBe 35
    }
})
