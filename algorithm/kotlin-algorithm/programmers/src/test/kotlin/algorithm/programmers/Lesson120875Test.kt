package algorithm.programmers

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class Lesson120875Test : StringSpec({
    "case-1" {
        val actual = Lesson120875().solution(
            arrayOf(
                intArrayOf(1, 4),
                intArrayOf(9, 2),
                intArrayOf(3, 8),
                intArrayOf(11, 6),
            )
        )
        actual shouldBe 1
    }

    "case-2" {
        val actual = Lesson120875().solution(
            arrayOf(
                intArrayOf(3, 5),
                intArrayOf(4, 1),
                intArrayOf(2, 4),
                intArrayOf(5, 10),
            )
        )
        actual shouldBe 0
    }
})
