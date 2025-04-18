package algorithm.programmers

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class Lesson12939Test : StringSpec({
    "case-1" {
        val s = "1 2 3 4"
        val actual = Lesson12939().solution(s)
        actual shouldBe "1 4"
    }

    "case-2" {
        val s = "-1 -2 -3 -4"
        val actual = Lesson12939().solution(s)
        actual shouldBe "-4 -1"
    }

    "case-3" {
        val s = "-1 -1"
        val actual = Lesson12939().solution(s)
        actual shouldBe "-1 -1"
    }
})
