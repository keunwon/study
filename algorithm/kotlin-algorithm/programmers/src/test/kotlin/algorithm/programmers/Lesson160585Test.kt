package algorithm.programmers

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class Lesson160585Test : StringSpec({
    "case-1" {
        val board = arrayOf("O.X", ".O.", "..X")
        val actual = Lesson160585().solution(board)
        actual shouldBe 1
    }

    "case-2" {
        val board = arrayOf("OOO", "...", "XXX")
        val actual = Lesson160585().solution(board)
        actual shouldBe 0
    }

    "case-3" {
        val board = arrayOf("...", ".X.", "...")
        val actual = Lesson160585().solution(board)
        actual shouldBe 0
    }

    "case-4" {
        val board = arrayOf("...", "...", "...")
        val actual = Lesson160585().solution(board)
        actual shouldBe 1
    }
})
