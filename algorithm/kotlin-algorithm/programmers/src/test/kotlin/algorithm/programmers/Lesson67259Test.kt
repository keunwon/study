package algorithm.programmers

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class Lesson67259Test : StringSpec({
    "case-1" {
        val board = arrayOf(
            intArrayOf(0, 0, 0),
            intArrayOf(0, 0, 0),
            intArrayOf(0, 0, 0)
        )
        val actual = Lesson67259().solution(board)
        actual shouldBe 900
    }

    "case-2" {
        val board = arrayOf(
            intArrayOf(0, 0, 0, 0, 0, 0, 0, 1),
            intArrayOf(0, 0, 0, 0, 0, 0, 0, 0),
            intArrayOf(0, 0, 0, 0, 0, 1, 0, 0),
            intArrayOf(0, 0, 0, 0, 1, 0, 0, 0),
            intArrayOf(0, 0, 0, 1, 0, 0, 0, 1),
            intArrayOf(0, 0, 1, 0, 0, 0, 1, 0),
            intArrayOf(0, 1, 0, 0, 0, 1, 0, 0),
            intArrayOf(1, 0, 0, 0, 0, 0, 0, 0)
        )
        val actual = Lesson67259().solution(board)
        actual shouldBe 3800
    }

    "case-3" {
        val board = arrayOf(
            intArrayOf(0, 0, 1, 0),
            intArrayOf(0, 0, 0, 0),
            intArrayOf(0, 1, 0, 1),
            intArrayOf(1, 0, 0, 0)
        )
        val actual = Lesson67259().solution(board)
        actual shouldBe 2100
    }

    "case-4" {
        val board = arrayOf(
            intArrayOf(0, 0, 0, 0, 0, 0),
            intArrayOf(0, 1, 1, 1, 1, 0),
            intArrayOf(0, 0, 1, 0, 0, 0),
            intArrayOf(1, 0, 0, 1, 0, 1),
            intArrayOf(0, 1, 0, 0, 0, 1),
            intArrayOf(0, 0, 0, 0, 0, 0)
        )

        val actual = Lesson67259().solution(board)
        actual shouldBe 3200
    }
})
