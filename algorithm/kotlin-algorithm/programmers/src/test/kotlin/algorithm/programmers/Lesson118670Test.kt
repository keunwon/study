package algorithm.programmers

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class Lesson118670Test : StringSpec({
    "case-1" {
        val rc = arrayOf(
            intArrayOf(1, 2, 3),
            intArrayOf(4, 5, 6),
            intArrayOf(7, 8, 9)
        )
        val operations = arrayOf("Rotate", "ShiftRow")

        val actual = Lesson118670().solution(rc, operations)

        actual shouldBe arrayOf(
            intArrayOf(8, 9, 6), intArrayOf(4, 1, 2), intArrayOf(7, 5, 3)
        )
    }

    "case-2" {
        val rc = arrayOf(
            intArrayOf(8, 6, 3),
            intArrayOf(3, 3, 7),
            intArrayOf(8, 4, 9)
        )
        val operations = arrayOf("Rotate", "ShiftRow", "ShiftRow")

        val actual = Lesson118670().solution(rc, operations)

        actual shouldBe arrayOf(
            intArrayOf(8, 3, 3),
            intArrayOf(4, 9, 7),
            intArrayOf(3, 8, 6)
        )
    }

    "case-3" {
        val rc = arrayOf(
            intArrayOf(1, 2, 3, 4),
            intArrayOf(5, 6, 7, 8),
            intArrayOf(9, 10, 11, 12)
        )
        val operations = arrayOf("ShiftRow", "Rotate", "ShiftRow", "Rotate")

        val actual = Lesson118670().solution(rc, operations)

        actual shouldBe arrayOf(intArrayOf(1, 6, 7, 8), intArrayOf(5, 9, 10, 4), intArrayOf(2, 3, 12, 11))
    }

    "case-4" {
        val rc = arrayOf(
            intArrayOf(1, 2),
            intArrayOf(3, 4),
        )
        val operations = arrayOf("Rotate", "ShiftRow")

        val actual = Lesson118670().solution(rc, operations)

        actual shouldBe arrayOf(intArrayOf(4, 2), intArrayOf(3, 1))
    }
})
