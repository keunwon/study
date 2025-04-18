package algorithm.programmers

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class Lesson68936Test : StringSpec({
    "case-1" {
        val map = arrayOf(
            intArrayOf(1, 1, 0, 0),
            intArrayOf(1, 0, 0, 0),
            intArrayOf(1, 0, 0, 1),
            intArrayOf(1, 1, 1, 1)
        )
        val actual = Lesson68936().solution(map)

        actual shouldBe intArrayOf(4, 9)
    }

    "case-2" {
        val map = arrayOf(
            intArrayOf(1, 1, 1, 1, 1, 1, 1, 1),
            intArrayOf(0, 1, 1, 1, 1, 1, 1, 1),
            intArrayOf(0, 0, 0, 0, 1, 1, 1, 1),
            intArrayOf(0, 1, 0, 0, 1, 1, 1, 1),
            intArrayOf(0, 0, 0, 0, 0, 0, 1, 1),
            intArrayOf(0, 0, 0, 0, 0, 0, 0, 1),
            intArrayOf(0, 0, 0, 0, 1, 0, 0, 1),
            intArrayOf(0, 0, 0, 0, 1, 1, 1, 1)
        )

        val actual = Lesson68936().solution(map)

        actual shouldBe intArrayOf(10, 15)
    }
})
