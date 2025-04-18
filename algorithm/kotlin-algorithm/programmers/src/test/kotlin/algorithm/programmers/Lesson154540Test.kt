package algorithm.programmers

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class Lesson154540Test : StringSpec({
    "case-1" {
        val maps = arrayOf("X591X", "X1X5X", "X231X", "1XXX1")
        val actual = Lesson154540().solution(maps)
        actual shouldBe intArrayOf(1, 1, 27)
    }

    "case-2" {
        val maps = arrayOf("XXX", "XXX", "XXX")
        val actual = Lesson154540().solution(maps)
        actual shouldBe intArrayOf(-1)
    }
})
