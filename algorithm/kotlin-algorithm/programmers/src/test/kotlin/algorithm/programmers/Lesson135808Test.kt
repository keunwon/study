package algorithm.programmers

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class Lesson135808Test : StringSpec({
    "case-1" {
        val k = 3
        val m = 4
        val score = intArrayOf(1, 2, 3, 1, 2, 3, 1)

        val actual = Lesson135808().solution(k, m, score)

        actual shouldBe 8
    }

    "case-2" {
        val k = 4
        val m = 3
        val scores = intArrayOf(4, 1, 2, 2, 4, 4, 4, 4, 1, 2, 4, 2)

        val actual = Lesson135808().solution(k, m, scores)

        actual shouldBe 33
    }
})
