package algorithm.programmers

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class Lesson62050Test : StringSpec({
    "case-1" {
        val land = arrayOf(
            intArrayOf(1, 4, 8, 10),
            intArrayOf(5, 5, 5, 5),
            intArrayOf(10, 10, 10, 10),
            intArrayOf(10, 10, 10, 20)
        )
        val height = 3

        val actual = Lesson62050().solution(land, height)

        actual shouldBe 15
    }

    "case-2" {
        val land = arrayOf(
            intArrayOf(10, 11, 10, 11),
            intArrayOf(2, 21, 20, 10),
            intArrayOf(1, 20, 21, 11),
            intArrayOf(2, 1, 2, 1)
        )
        val height = 1

        val actual = Lesson62050().solution(land, height)

        actual shouldBe 18
    }
})
