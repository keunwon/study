package algorithm.programmers

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class Lesson136797Test : StringSpec({
    "case-1" {
        val numbers = "1756"
        val actual = Lesson136797().solution(numbers)
        actual shouldBe 10
    }

    "case-2" {
        val numbers = "5123"
        val actual = Lesson136797().solution(numbers)
        actual shouldBe 8
    }
})
