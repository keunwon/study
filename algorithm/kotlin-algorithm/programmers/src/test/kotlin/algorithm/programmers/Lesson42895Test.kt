package algorithm.programmers

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class Lesson42895Test : StringSpec({
    "case-1" {
        val n = 5
        val number = 12

        val actual = Lesson42895().solution(n, number)

        actual shouldBe 4
    }

    "case-2" {
        val n = 2
        val number = 11

        val actual = Lesson42895().solution(n, number)

        actual shouldBe 3
    }
})
