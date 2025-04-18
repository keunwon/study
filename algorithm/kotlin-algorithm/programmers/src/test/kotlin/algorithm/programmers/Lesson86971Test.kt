package algorithm.programmers

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class Lesson86971Test : StringSpec({
    "case-1" {
        val n = 9
        val wires = arrayOf(
            intArrayOf(1, 3),
            intArrayOf(2, 3),
            intArrayOf(3, 4),
            intArrayOf(4, 5),
            intArrayOf(4, 6),
            intArrayOf(4, 7),
            intArrayOf(7, 8),
            intArrayOf(7, 9)
        )

        val actual = Lesson86971().solution(n, wires)

        actual shouldBe 3
    }

    "case-2" {
        val n = 4
        val wires = arrayOf(
            intArrayOf(1, 2), intArrayOf(2, 3), intArrayOf(3, 4)
        )

        val actual = Lesson86971().solution(n, wires)

        actual shouldBe 0
    }

    "case-3" {
        val n = 7
        val wires = arrayOf(
            intArrayOf(1, 2),
            intArrayOf(2, 7),
            intArrayOf(3, 7),
            intArrayOf(3, 4),
            intArrayOf(4, 5),
            intArrayOf(6, 7)
        )

        val actual = Lesson86971().solution(n, wires)

        actual shouldBe 1
    }
})
