package algorithm.programmers

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class Lesson120860Test : StringSpec({
    "case-1" {
        val dots = arrayOf(
            intArrayOf(1, 1),
            intArrayOf(2, 1),
            intArrayOf(2, 2),
            intArrayOf(1, 2)
        )
        val actual = Lesson120860().solution(dots)
        actual shouldBe 1
    }

    "case-2" {
        val dots = arrayOf(
            intArrayOf(-1, -1),
            intArrayOf(1, 1),
            intArrayOf(1, -1),
            intArrayOf(-1, 1)
        )
        val actual = Lesson120860().solution(dots)
        actual shouldBe 4
    }
})
