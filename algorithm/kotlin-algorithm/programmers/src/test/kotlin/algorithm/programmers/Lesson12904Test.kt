package algorithm.programmers

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class Lesson12904Test : StringSpec({
    "case-1" {
        val s = "abcdcba"
        val actual = Lesson12904().solution(s)
        actual shouldBe 7
    }

    "case-2" {
        val s = "abacde"
        val actual = Lesson12904().solution(s)
        actual shouldBe 3
    }
})
