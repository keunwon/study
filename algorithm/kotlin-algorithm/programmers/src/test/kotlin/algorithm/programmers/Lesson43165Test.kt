package algorithm.programmers

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class Lesson43165Test : StringSpec({
    "case-1" {
        val numbers = intArrayOf(1, 1, 1, 1, 1)
        val target = 3

        val actual = Lesson43165().solution(numbers, target)

        actual shouldBe 5
    }

    "case-2" {
        val numbers = intArrayOf(4, 1, 2, 1)
        val target = 4

        val actual = Lesson43165().solution(numbers, target)

        actual shouldBe 2
    }
})
