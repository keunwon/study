package algorithm.programmers

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class Lesson181897Test : StringSpec({
    "case-1" {
        val n = 3
        val slicer = intArrayOf(1, 5, 2)
        val num_list = intArrayOf(1, 2, 3, 4, 5, 6, 7, 8, 9)

        val actual = Lesson181897().solution(n, slicer, num_list)

        actual shouldBe intArrayOf(2, 3, 4, 5, 6)
    }

    "case-2" {
        val n = 4
        val slicer = intArrayOf(1, 5, 2)
        val num_list = intArrayOf(1, 2, 3, 4, 5, 6, 7, 8, 9)

        val actual = Lesson181897().solution(n, slicer, num_list)

        actual shouldBe intArrayOf(2, 4, 6)
    }
})
