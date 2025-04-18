package algorithm.programmers

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class Lesson389479Test : StringSpec({
    "case-1" {
        val players = intArrayOf(0, 2, 3, 3, 1, 2, 0, 0, 0, 0, 4, 2, 0, 6, 0, 4, 2, 13, 3, 5, 10, 0, 1, 5)
        val m = 3
        val k = 5

        val actual = Lesson389479().solution(players, m, k)

        actual shouldBe 7
    }

    "case-2" {
        val players = intArrayOf(0, 0, 0, 10, 0, 12, 0, 15, 0, 1, 0, 1, 0, 0, 0, 5, 0, 0, 11, 0, 8, 0, 0, 0)
        val m = 5
        val k = 1

        val actual = Lesson389479().solution(players, m, k)

        actual shouldBe 11
    }

    "case-3" {
        val players = intArrayOf(0, 0, 0, 0, 0, 2, 0, 0, 0, 1, 0, 5, 0, 2, 0, 1, 0, 0, 0, 0, 0, 0, 0, 1)
        val m = 1
        val k = 1

        val actual = Lesson389479().solution(players, m, k)

        actual shouldBe 12
    }
})
