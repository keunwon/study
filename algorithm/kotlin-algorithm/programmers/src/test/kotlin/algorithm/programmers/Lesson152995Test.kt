package algorithm.programmers

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class Lesson152995Test : StringSpec({
    "case-1" {
        val scores = arrayOf(
            intArrayOf(2, 2),
            intArrayOf(1, 4),
            intArrayOf(3, 2),
            intArrayOf(3, 2),
            intArrayOf(2, 1)
        )

        val actual = Lesson152995().solution(scores)

        actual shouldBe 4
    }
})
