package algorithm.programmers

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class Lesson49189Test : StringSpec({
    "case-1" {
        val n = 6
        val edge = arrayOf(
            intArrayOf(3, 6),
            intArrayOf(4, 3),
            intArrayOf(3, 2),
            intArrayOf(1, 3),
            intArrayOf(1, 2),
            intArrayOf(2, 4),
            intArrayOf(5, 2)
        )

        val actual = Lesson49189().solution(n, edge)

        actual shouldBe 3
    }
})
