package algorithm.programmers

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class Lesson92345Test : StringSpec({
    "case-1" {
        val board = arrayOf(
            intArrayOf(1, 1, 1), intArrayOf(1, 1, 1), intArrayOf(1, 1, 1)
        )
        val aloc = intArrayOf(1, 0)
        val bloc = intArrayOf(1, 2)

        val actual = Lesson92345().solution(board, aloc, bloc)

        actual shouldBe 5
    }

    "case-2" {
        val board = arrayOf(
            intArrayOf(1, 1, 1), intArrayOf(1, 0, 1), intArrayOf(1, 1, 1)
        )
        val alco = intArrayOf(1, 0)
        val bloc = intArrayOf(1, 2)

        val actual = Lesson92345().solution(board, alco, bloc)

        actual shouldBe 4
    }

    "case-3" {
        val board = arrayOf(
            intArrayOf(1, 1, 1, 1, 1)
        )
        val aloc = intArrayOf(0, 0)
        val bloc = intArrayOf(0, 4)

        val actual = Lesson92345().solution(board, aloc, bloc)

        actual shouldBe 4
    }

    "case-4" {
        val board = arrayOf(
            intArrayOf(1)
        )
        val aloc = intArrayOf(0, 0)
        val bloc = intArrayOf(0, 0)

        val actual = Lesson92345().solution(board, aloc, bloc)

        actual shouldBe 0
    }
})
