package algorithm.programmers

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class Lesson154538Test : StringSpec({
    "case-1" {
        val x = 10
        val y = 40
        val n = 5

        val actual = Lesson154538().solution(x, y, n)

        actual shouldBe 2
    }

    "case-2" {
        val x = 10
        val y = 40
        val n = 30

        val actual = Lesson154538().solution(x, y, n)

        actual shouldBe 1
    }

    "case-3" {
        val x = 2
        val y = 5
        val n = 4

        val actual = Lesson154538().solution(x, y, n)

        actual shouldBe -1
    }
})
