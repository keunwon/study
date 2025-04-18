package algorithm.programmers

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class Lesson147354Test : StringSpec({
    "case-1" {
        val data = arrayOf(
            intArrayOf(2, 2, 6), intArrayOf(1, 5, 10), intArrayOf(4, 2, 9), intArrayOf(3, 8, 3)
        )
        val col = 2
        val row_begin = 2
        val row_end = 3

        val actual = Lesson147354().solution(data, col, row_begin, row_end)

        actual shouldBe 4
    }
})
