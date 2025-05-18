package algorithm.programmers

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class Lesson136798Test : StringSpec({
    "case-1" {
        val number = 5
        val limit = 3
        val power = 2

        val actual = Lesson136798().solution(number, limit, power)

        actual shouldBe 10
    }

    "case-2" {
        val number = 10
        val limit = 3
        val power = 2

        val actual = Lesson136798().solution(number, limit, power)

        actual shouldBe 21
    }
})
