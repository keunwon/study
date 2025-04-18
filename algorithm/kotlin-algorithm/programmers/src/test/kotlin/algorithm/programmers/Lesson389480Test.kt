package algorithm.programmers

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class Lesson389480Test : StringSpec({
    "case-1" {
        val info = arrayOf(intArrayOf(1, 2), intArrayOf(2, 3), intArrayOf(2, 1))
        val n = 4
        val m = 4

        val actual = Lesson389480().solution(info, n, m)

        actual shouldBe 2
    }

    "case-2" {
        val info = arrayOf(
            intArrayOf(1, 2),
            intArrayOf(2, 3),
            intArrayOf(2, 1),
        )
        val n = 1
        val m = 7

        val actual = Lesson389480().solution(info, n, m)

        actual shouldBe 0
    }

    "case-3" {
        val info = arrayOf(intArrayOf(3, 3), intArrayOf(3, 3))
        val n = 7
        val m = 1

        val actual = Lesson389480().solution(info, n, m)

        actual shouldBe 6
    }

    "case-4" {
        val info = arrayOf(intArrayOf(3, 3), intArrayOf(3, 3))
        val n = 6
        val m = 1

        val actual = Lesson389480().solution(info, n, m)

        actual shouldBe -1
    }
})
