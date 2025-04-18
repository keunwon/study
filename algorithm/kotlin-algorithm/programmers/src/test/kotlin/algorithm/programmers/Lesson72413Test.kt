package algorithm.programmers

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class Lesson72413Test : StringSpec({
    "case-1" {
        val n = 6
        val s = 4
        val a = 6
        val b = 2
        val fares = arrayOf(
            intArrayOf(4, 1, 10),
            intArrayOf(3, 5, 24),
            intArrayOf(5, 6, 2),
            intArrayOf(3, 1, 41),
            intArrayOf(5, 1, 24),
            intArrayOf(4, 6, 50),
            intArrayOf(2, 4, 66),
            intArrayOf(2, 3, 22),
            intArrayOf(1, 6, 25)
        )

        val actual = Lesson72413().solution(n, s, a, b, fares)

        actual shouldBe 82
    }

    "case-2" {
        val n = 7
        val s = 3
        val a = 4
        val b = 1
        val fares = arrayOf(
            intArrayOf(5, 7, 9),
            intArrayOf(4, 6, 4),
            intArrayOf(3, 6, 1),
            intArrayOf(3, 2, 3),
            intArrayOf(2, 1, 6)
        )

        val actual = Lesson72413().solution(n, s, a, b, fares)

        actual shouldBe 14
    }

    "case-3" {
        val n = 6
        val s = 4
        val a = 5
        val b = 6
        val fares = arrayOf(
            intArrayOf(2, 6, 6),
            intArrayOf(6, 3, 7),
            intArrayOf(4, 6, 7),
            intArrayOf(6, 5, 11),
            intArrayOf(2, 5, 12),
            intArrayOf(5, 3, 20),
            intArrayOf(2, 4, 8),
            intArrayOf(4, 3, 9)
        )

        val actual = Lesson72413().solution(n, s, a, b, fares)

        actual shouldBe 18
    }
})
