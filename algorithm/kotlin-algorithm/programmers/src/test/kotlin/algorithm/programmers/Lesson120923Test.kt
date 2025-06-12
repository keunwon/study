package algorithm.programmers

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class Lesson120923Test : StringSpec({
    "case-1" {
        val actual = Lesson120923().solution(
            num = 3,
            total = 12,
        )
        actual shouldBe intArrayOf(3, 4, 5)
    }

    "case-2" {
        val actual = Lesson120923().solution(
            num = 5,
            total = 15,
        )
        actual shouldBe intArrayOf(1, 2, 3, 4, 5)
    }

    "case-3" {
        val actual = Lesson120923().solution(
            num = 4,
            total = 14,
        )
        actual shouldBe intArrayOf(2, 3, 4, 5)
    }

    "case-6" {
        val actual = Lesson120923().solution(
            num = 5,
            total = 5,
        )
        actual shouldBe intArrayOf(-1, 0, 1, 2, 3)
    }
})
