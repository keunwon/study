package algorithm.programmers

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class Lesson81305Test : StringSpec({
    "case-1" {
        val k = 3
        val num = intArrayOf(12, 30, 1, 8, 8, 6, 20, 7, 5, 10, 4, 1)
        val links = arrayOf(
            intArrayOf(-1, -1),
            intArrayOf(-1, -1),
            intArrayOf(-1, -1),
            intArrayOf(-1, -1),
            intArrayOf(8, 5),
            intArrayOf(2, 10),
            intArrayOf(3, 0),
            intArrayOf(6, 1),
            intArrayOf(11, -1),
            intArrayOf(7, 4),
            intArrayOf(-1, -1),
            intArrayOf(-1, -1),
        )

        val actual = Lesson81305().solution(k, num, links)

        actual shouldBe 40
    }

    "case-2" {
        val k = 1
        val num = intArrayOf(6, 9, 7, 5)
        val links = arrayOf(
            intArrayOf(-1, -1),
            intArrayOf(-1, -1),
            intArrayOf(-1, 0),
            intArrayOf(2, 1),
        )

        val actual = Lesson81305().solution(k, num, links)

        actual shouldBe 27
    }

    "case-3" {
        val k = 2
        val num = intArrayOf(6, 9, 7, 5)
        val links = arrayOf(intArrayOf(-1, -1), intArrayOf(-1, -1), intArrayOf(-1, 0), intArrayOf(2, 1))

        val actual = Lesson81305().solution(k, num, links)

        actual shouldBe 14
    }

    "case-4" {
        val k = 4
        val num = intArrayOf(6, 9, 7, 5)
        val links = arrayOf(
            intArrayOf(-1, -1), intArrayOf(-1, -1), intArrayOf(-1, 0), intArrayOf(2, 1)
        )

        val actual = Lesson81305().solution(k, num, links)

        actual shouldBe 9
    }
})
