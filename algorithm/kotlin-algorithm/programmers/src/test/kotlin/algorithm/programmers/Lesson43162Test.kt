package algorithm.programmers

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class Lesson43162Test : StringSpec({
    "case-1" {
        val n = 3
        val computers = arrayOf(
            intArrayOf(1, 1, 0),
            intArrayOf(1, 1, 0),
            intArrayOf(0, 0, 1)
        )

        val actual = Lesson43162().solution(n, computers)

        actual shouldBe 2
    }

    "case-2" {
        val n = 3
        val computers = arrayOf(
            intArrayOf(1, 1, 0),
            intArrayOf(1, 1, 1),
            intArrayOf(0, 1, 1)
        )

        val actual = Lesson43162().solution(n, computers)

        actual shouldBe 1
    }
})
