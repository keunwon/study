package algorithm.programmers

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class Lesson340211Test : StringSpec({
    "case-1" {
        val points = arrayOf(
            intArrayOf(3, 2),
            intArrayOf(6, 4),
            intArrayOf(4, 7),
            intArrayOf(1, 4),
        )
        val routes = arrayOf(
            intArrayOf(4, 2),
            intArrayOf(1, 3),
            intArrayOf(2, 4),
        )

        val actual = Lesson340211().solution(points, routes)

        actual shouldBe 1
    }

    "case-2" {
        val points = arrayOf(
            intArrayOf(3, 2),
            intArrayOf(6, 4),
            intArrayOf(4, 7),
            intArrayOf(1, 4),
        )
        val routes = arrayOf(
            intArrayOf(4, 2),
            intArrayOf(1, 3),
            intArrayOf(4, 2),
            intArrayOf(4, 3),
        )

        val actual = Lesson340211().solution(points, routes)

        actual shouldBe 9
    }

    "case-3" {
        val points = arrayOf(
            intArrayOf(2, 2),
            intArrayOf(2, 3),
            intArrayOf(2, 7),
            intArrayOf(6, 6),
            intArrayOf(5, 2)
        )
        val routes = arrayOf(
            intArrayOf(2, 3, 4, 5),
            intArrayOf(1, 3, 4, 5)
        )

        val actual = Lesson340211().solution(points, routes)

        actual shouldBe 0
    }
})
