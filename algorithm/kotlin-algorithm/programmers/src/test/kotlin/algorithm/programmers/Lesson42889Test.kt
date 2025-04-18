package algorithm.programmers

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class Lesson42889Test : StringSpec({
    "case-1" {
        val n = 5
        val stages = intArrayOf(2, 1, 2, 6, 2, 4, 3, 3)

        val actual = Lesson42889().solution(n, stages)

        actual shouldBe intArrayOf(3, 4, 2, 1, 5)
    }

    "case-2" {
        val n = 4
        val stages = intArrayOf(4, 4, 4, 4, 4)

        val actual = Lesson42889().solution(n, stages)

        actual shouldBe intArrayOf(4, 1, 2, 3)
    }
})
