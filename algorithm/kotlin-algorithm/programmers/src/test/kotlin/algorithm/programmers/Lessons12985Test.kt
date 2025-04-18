package algorithm.programmers

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class Lessons12985Test : StringSpec({
    "case-1" {
        val n = 8
        val a = 4
        val b = 7

        val actual = Lesson12985().solution(n, a, b)

        actual shouldBe 3
    }
})
