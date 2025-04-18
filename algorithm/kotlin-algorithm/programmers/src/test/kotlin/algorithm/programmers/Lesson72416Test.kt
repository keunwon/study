package algorithm.programmers

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class Lesson72416Test : StringSpec({
    "case-1" {
        val sales = intArrayOf(14, 17, 15, 18, 19, 14, 13, 16, 28, 17)
        val links = arrayOf(
            intArrayOf(10, 8),
            intArrayOf(1, 9),
            intArrayOf(9, 7),
            intArrayOf(5, 4),
            intArrayOf(1, 5),
            intArrayOf(5, 10),
            intArrayOf(10, 6),
            intArrayOf(1, 3),
            intArrayOf(10, 2)
        )

        val actual = Lesson72416().solution(sales, links)

        actual shouldBe 44
    }

    "case-2" {
        val sales = intArrayOf(5, 6, 5, 3, 4)
        val links = arrayOf(
            intArrayOf(2, 3),
            intArrayOf(1, 4),
            intArrayOf(2, 5),
            intArrayOf(1, 2)
        )

        val actual = Lesson72416().solution(sales, links)

        actual shouldBe 6
    }

    "case-3" {
        val sales = intArrayOf(5, 6, 5, 1, 4)
        val links = arrayOf(
            intArrayOf(2, 3),
            intArrayOf(1, 4),
            intArrayOf(2, 5),
            intArrayOf(1, 2)
        )

        val actual = Lesson72416().solution(sales, links)

        actual shouldBe 5
    }

    "case-4" {
        val sales = intArrayOf(10, 10, 1, 1)
        val links = arrayOf(
            intArrayOf(3, 2),
            intArrayOf(4, 3),
            intArrayOf(1, 4)
        )

        val actual = Lesson72416().solution(sales, links)

        actual shouldBe 2
    }
})
