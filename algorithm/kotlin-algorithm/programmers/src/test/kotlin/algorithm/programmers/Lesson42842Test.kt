package algorithm.programmers

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class Lesson42842Test : StringSpec({
    "case-1" {
        val brown = 10
        val yellow = 2

        val actual = Lesson42842().solution(brown, yellow)

        actual shouldBe intArrayOf(4, 3)
    }

    "case-2" {
        val brown = 8
        val yellow = 1

        val actual = Lesson42842().solution(brown, yellow)

        actual shouldBe intArrayOf(3, 3)
    }

    "case-3" {
        val brown = 24
        val yellow = 24

        val actual = Lesson42842().solution(brown, yellow)

        actual shouldBe intArrayOf(8, 6)
    }
})
