package algorithm.programmers

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class Lesson134239Test : StringSpec({
    "case-1" {
        val k = 5
        val ranges = arrayOf(
            intArrayOf(0, 0),
            intArrayOf(0, -1),
            intArrayOf(2, -3),
            intArrayOf(3, -3)
        )

        val actual = Lesson134239().solution(k, ranges)

        actual shouldBe doubleArrayOf(33.0, 31.5, 0.0, -1.0)
    }

    "case-2" {
        val k = 3
        val ranges = arrayOf(
            intArrayOf(0, 0),
            intArrayOf(1, -2),
            intArrayOf(3, -3)
        )

        val actual = Lesson134239().solution(k, ranges)

        actual shouldBe doubleArrayOf(47.0, 36.0, 12.0)
    }
})
