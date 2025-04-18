package algorithm.programmers

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class Lesson42892Test : StringSpec({
    "case-1" {
        val nodeinfo = arrayOf(
            intArrayOf(5, 3),
            intArrayOf(11, 5),
            intArrayOf(13, 3),
            intArrayOf(3, 5),
            intArrayOf(6, 1),
            intArrayOf(1, 3),
            intArrayOf(8, 6),
            intArrayOf(7, 2),
            intArrayOf(2, 2)
        )
        val actual = Lesson42892().solution(nodeinfo)

        actual shouldBe arrayOf(
            intArrayOf(7, 4, 6, 9, 1, 8, 5, 2, 3),
            intArrayOf(9, 6, 5, 8, 1, 4, 3, 2, 7)
        )
    }
})
