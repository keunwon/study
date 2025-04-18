package algorithm.programmers

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class Lesson68644Test : StringSpec({
    "case-1" {
        val numbers = intArrayOf(2, 1, 3, 4, 1)
        val actual = Lesson68644().solution(numbers)
        actual shouldBe intArrayOf(2, 3, 4, 5, 6, 7)
    }

    "case-2" {
        val numbers = intArrayOf(5, 0, 2, 7)
        val actual = Lesson68644().solution(numbers)
        actual shouldBe intArrayOf(2, 5, 7, 9, 12)
    }
})
