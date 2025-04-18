package algorithm.programmers

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class Lesson258709Test : StringSpec({
    "case-1" {
        val dice = arrayOf(
            intArrayOf(1, 2, 3, 4, 5, 6),
            intArrayOf(3, 3, 3, 3, 4, 4),
            intArrayOf(1, 3, 3, 4, 4, 4),
            intArrayOf(1, 1, 4, 4, 5, 5)
        )
        val actual = Lesson258709().solution(dice)
        actual shouldBe intArrayOf(1, 4)
    }

    "case-2" {
        val dice = arrayOf(
            intArrayOf(1, 2, 3, 4, 5, 6),
            intArrayOf(2, 2, 4, 4, 6, 6)
        )
        val actual = Lesson258709().solution(dice)
        actual shouldBe intArrayOf(2)
    }

    "case-3" {
        val dice = arrayOf(
            intArrayOf(40, 41, 42, 43, 44, 45),
            intArrayOf(43, 43, 42, 42, 41, 41),
            intArrayOf(1, 1, 80, 80, 80, 80),
            intArrayOf(70, 70, 1, 1, 70, 70)
        )
        val actual = Lesson258709().solution(dice)
        actual shouldBe intArrayOf(1, 3)
    }
})
