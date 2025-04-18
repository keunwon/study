package algorithm.programmers

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class Lesson84021Test : StringSpec({
    "case-1" {
        val game_board = arrayOf(
            intArrayOf(1, 1, 0, 0, 1, 0),
            intArrayOf(0, 0, 1, 0, 1, 0),
            intArrayOf(0, 1, 1, 0, 0, 1),
            intArrayOf(1, 1, 0, 1, 1, 1),
            intArrayOf(1, 0, 0, 0, 1, 0),
            intArrayOf(0, 1, 1, 1, 0, 0)
        )
        val table = arrayOf(
            intArrayOf(1, 0, 0, 1, 1, 0),
            intArrayOf(1, 0, 1, 0, 1, 0),
            intArrayOf(0, 1, 1, 0, 1, 1),
            intArrayOf(0, 0, 1, 0, 0, 0),
            intArrayOf(1, 1, 0, 1, 1, 0),
            intArrayOf(0, 1, 0, 0, 0, 0)
        )

        val actual = Lesson84021().solution(game_board, table)

        actual shouldBe 14
    }

    "case-2" {
        val game_board = arrayOf(
            intArrayOf(0, 0, 0),
            intArrayOf(1, 1, 0),
            intArrayOf(1, 1, 1)
        )
        val table = arrayOf(
            intArrayOf(1, 1, 1),
            intArrayOf(1, 0, 0),
            intArrayOf(0, 0, 0)
        )

        val actual = Lesson84021().solution(game_board, table)

        actual shouldBe 0
    }
})
