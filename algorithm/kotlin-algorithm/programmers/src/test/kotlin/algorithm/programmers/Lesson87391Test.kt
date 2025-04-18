package algorithm.programmers

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class Lesson87391Test : StringSpec({
    "case-1" {
        val n = 2
        val m = 2
        val x = 0
        val y = 0
        val queries = arrayOf(
            intArrayOf(2, 1),
            intArrayOf(0, 1),
            intArrayOf(1, 1),
            intArrayOf(0, 1),
            intArrayOf(2, 1)
        )

        val actual = Lesson87391().solution(n, m, x, y, queries)

        actual shouldBe 4
    }

    "case-2" {
        val n = 2
        val m = 5
        val x = 0
        val y = 1
        val queries = arrayOf(
            intArrayOf(3, 1),
            intArrayOf(2, 2),
            intArrayOf(1, 1),
            intArrayOf(2, 3),
            intArrayOf(0, 1),
            intArrayOf(2, 1)
        )

        val actual = Lesson87391().solution(n, m, x, y, queries)

        actual shouldBe 2
    }
})
