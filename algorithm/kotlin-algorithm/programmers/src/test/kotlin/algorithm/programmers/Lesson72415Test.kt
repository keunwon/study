package algorithm.programmers

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class Lesson72415Test : StringSpec({
    "case-1" {
        val board = arrayOf(
            intArrayOf(1, 0, 0, 3),
            intArrayOf(2, 0, 0, 0),
            intArrayOf(0, 0, 0, 2),
            intArrayOf(3, 0, 1, 0)
        )
        val r = 1
        val c = 0

        val actual = Lesson72415().solution(board, r, c)

        actual shouldBe 14
    }

    "case-2" {
        val board = arrayOf(
            intArrayOf(3, 0, 0, 2),
            intArrayOf(0, 0, 1, 0),
            intArrayOf(0, 1, 0, 0),
            intArrayOf(2, 0, 0, 3)
        )
        val r = 0
        val c = 1

        val actual = Lesson72415().solution(board, r, c)

        actual shouldBe 16
    }
})
