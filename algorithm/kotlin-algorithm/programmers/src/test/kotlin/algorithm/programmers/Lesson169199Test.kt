package algorithm.programmers

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class Lesson169199Test : StringSpec({
    "case-1" {
        val board = arrayOf("...D..R", ".D.G...", "....D.D", "D....D.", "..D....")
        val actual = Lesson169199().solution(board)
        actual shouldBe 7
    }

    "case-2" {
        val board = arrayOf(".D.R", "....", ".G..", "...D")
        val actual = Lesson169199().solution(board)
        actual shouldBe -1
    }
})
