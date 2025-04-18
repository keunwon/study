package algorithm.programmers

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class Lesson12926Test : StringSpec({
    "case-1" {
        val s = "AB"
        val n = 1

        val actual = Lesson12926().solution(s, n)

        actual shouldBe "BC"
    }

    "case-2" {
        val s = "z"
        val n = 1

        val actual = Lesson12926().solution(s, n)

        actual shouldBe "a"
    }

    "case-3" {
        val s = "a B z"
        val n = 4

        val actual = Lesson12926().solution(s, n)

        actual shouldBe "e F d"
    }
})
