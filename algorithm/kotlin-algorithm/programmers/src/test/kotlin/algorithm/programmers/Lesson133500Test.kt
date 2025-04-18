package algorithm.programmers

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class Lesson133500Test : StringSpec({
    "case-1" {
        val n = 8
        val lighthouse = arrayOf(
            intArrayOf(1, 2),
            intArrayOf(1, 3),
            intArrayOf(1, 4),
            intArrayOf(1, 5),
            intArrayOf(5, 6),
            intArrayOf(5, 7),
            intArrayOf(5, 8)
        )

        val actual = Lesson133500().solution(n, lighthouse)

        actual shouldBe 2
    }

    "case-2" {
        val n = 10
        val lighthouse = arrayOf(
            intArrayOf(4, 1),
            intArrayOf(5, 1),
            intArrayOf(5, 6),
            intArrayOf(7, 6),
            intArrayOf(1, 2),
            intArrayOf(1, 3),
            intArrayOf(6, 8),
            intArrayOf(2, 9),
            intArrayOf(9, 10)
        )

        val actual = Lesson133500().solution(n, lighthouse)

        actual shouldBe 3
    }
})
