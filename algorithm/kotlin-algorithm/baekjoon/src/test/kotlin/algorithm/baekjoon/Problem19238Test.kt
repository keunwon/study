package algorithm.baekjoon

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class Problem19238Test : StringSpec({
    "case-1" {
        val fuel = 15
        val map = arrayOf(
            intArrayOf(0, 0, 1, 0, 0, 0),
            intArrayOf(0, 0, 1, 0, 0, 0),
            intArrayOf(0, 0, 0, 0, 0, 0),
            intArrayOf(0, 0, 0, 0, 0, 0),
            intArrayOf(0, 0, 0, 0, 1, 0),
            intArrayOf(0, 0, 0, 1, 0, 0),
        )
        val driver = 6 to 5
        val routes = arrayOf(
            intArrayOf(2, 2, 5, 6),
            intArrayOf(5, 4, 1, 6),
            intArrayOf(4, 2, 3, 5),
        )

        val actual = Problem19238().solution(fuel, map, driver, routes)

        actual shouldBe 14
    }

    "case-2" {
        val fuel = 13
        val map = arrayOf(
            intArrayOf(0, 0, 1, 0, 0, 0),
            intArrayOf(0, 0, 1, 0, 0, 0),
            intArrayOf(0, 0, 0, 0, 0, 0),
            intArrayOf(0, 0, 0, 0, 0, 0),
            intArrayOf(0, 0, 0, 0, 1, 0),
            intArrayOf(0, 0, 0, 1, 0, 0),
        )
        val driver = 6 to 5
        val routes = arrayOf(
            intArrayOf(2, 2, 5, 6),
            intArrayOf(5, 4, 1, 6),
            intArrayOf(4, 2, 3, 5),
        )

        val actual = Problem19238().solution(fuel, map, driver, routes)

        actual shouldBe -1
    }

    "case-3" {
        val fuel = 100
        val map = arrayOf(
            intArrayOf(0, 0, 1, 0, 0, 0),
            intArrayOf(0, 0, 1, 0, 0, 0),
            intArrayOf(0, 0, 0, 1, 0, 0),
            intArrayOf(0, 0, 0, 1, 0, 0),
            intArrayOf(0, 0, 0, 0, 1, 0),
            intArrayOf(0, 0, 0, 1, 0, 0),
        )
        val driver = 6 to 5
        val routes = arrayOf(
            intArrayOf(2, 2, 5, 6),
            intArrayOf(5, 4, 1, 6),
            intArrayOf(4, 2, 3, 5),
        )

        val actual = Problem19238().solution(fuel, map, driver, routes)

        actual shouldBe -1
    }
})
