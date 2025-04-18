package algorithm.programmers

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class Lesson87390Test : StringSpec({
    "case-1" {
        val n = 3
        val left = 2L
        val right = 5L

        val actual = Lesson87390().solution(n, left, right)

        actual shouldBe longArrayOf(3, 2, 2, 3)
    }

    "case-2" {
        val n = 4
        val left = 7L
        val right = 14L

        val actual = Lesson87390().solution(n, left, right)

        actual shouldBe longArrayOf(4, 3, 3, 3, 4, 4, 4, 4)
    }
})
