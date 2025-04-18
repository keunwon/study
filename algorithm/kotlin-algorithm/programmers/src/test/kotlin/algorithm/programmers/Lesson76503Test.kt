package algorithm.programmers

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class Lesson76503Test : StringSpec({
    "case-1" {
        val a = intArrayOf(-5, 0, 2, 1, 2)
        val edges = arrayOf(
            intArrayOf(0, 1),
            intArrayOf(3, 4),
            intArrayOf(2, 3),
            intArrayOf(0, 3)
        )

        val actual = Lesson76503().solution(a, edges)

        actual shouldBe 9
    }

    "case-2" {
        val a = intArrayOf(0, 1, 0)
        val edges = arrayOf(
            intArrayOf(0, 1),
            intArrayOf(1, 2)
        )

        val actual = Lesson76503().solution(a, edges)

        actual shouldBe -1
    }
})
