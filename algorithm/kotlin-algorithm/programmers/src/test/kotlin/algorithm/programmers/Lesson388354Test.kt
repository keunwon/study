package algorithm.programmers

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class Lesson388354Test : StringSpec({
    "case-1" {
        val nodes = intArrayOf(11, 9, 3, 2, 4, 6)
        val edges = arrayOf(
            intArrayOf(9, 11),
            intArrayOf(2, 3),
            intArrayOf(6, 3),
            intArrayOf(3, 4),
        )

        val actual = Lesson388354().solution(nodes, edges)

        actual shouldBe intArrayOf(1, 0)
    }

    "case-2" {
        val nodes = intArrayOf(9, 15, 14, 7, 6, 1, 2, 4, 5, 11, 8, 10)
        val edges = arrayOf(
            intArrayOf(5, 14),
            intArrayOf(1, 4),
            intArrayOf(9, 11),
            intArrayOf(2, 15),
            intArrayOf(2, 5),
            intArrayOf(9, 7),
            intArrayOf(8, 1),
            intArrayOf(6, 4),
        )

        val actual = Lesson388354().solution(nodes, edges)

        actual shouldBe intArrayOf(2, 1)
    }
})
