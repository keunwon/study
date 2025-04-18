package algorithm.programmers

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class Lesson12934Test : StringSpec({
    "case-1" {
        val n = 121L
        val actual = Lesson12934().solution(n)
        actual shouldBe 144L
    }

    "case-2" {
        val n = 3L
        val actual = Lesson12934().solution(n)
        actual shouldBe -1L
    }
})
