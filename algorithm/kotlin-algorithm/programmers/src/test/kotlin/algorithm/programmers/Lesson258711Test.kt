package algorithm.programmers

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class Lesson258711Test : StringSpec({
    "case-1" {
        val edges = arrayOf(
            intArrayOf(2, 3),
            intArrayOf(4, 3),
            intArrayOf(1, 1),
            intArrayOf(2, 1)
        )

        val actual = Lesson258711().solution(edges)

        actual shouldBe intArrayOf(2, 1, 1, 0)
    }

    "case-2" {
        val edges = arrayOf(
            intArrayOf(4, 11),
            intArrayOf(1, 12),
            intArrayOf(8, 3),
            intArrayOf(12, 7),
            intArrayOf(4, 2),
            intArrayOf(7, 11),
            intArrayOf(4, 8),
            intArrayOf(9, 6),
            intArrayOf(10, 11),
            intArrayOf(6, 10),
            intArrayOf(3, 5),
            intArrayOf(11, 1),
            intArrayOf(5, 3),
            intArrayOf(11, 9),
            intArrayOf(3, 8)
        )

        val actual = Lesson258711().solution(edges)

        actual shouldBe intArrayOf(4, 0, 1, 2)
    }
})
