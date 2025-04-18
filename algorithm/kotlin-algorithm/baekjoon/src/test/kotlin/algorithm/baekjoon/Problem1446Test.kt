package algorithm.baekjoon

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class Problem1446Test : StringSpec({
    "case-1" {
        val d = 150
        val routes = arrayOf(
            intArrayOf(0, 50, 10),
            intArrayOf(0, 50, 20),
            intArrayOf(50, 100, 10),
            intArrayOf(100, 151, 10),
            intArrayOf(110, 140, 90),
        )

        val actual = Problem1446().solution(d, routes)

        actual shouldBe 70
    }

    "case-2" {
        val d = 100
        val routes = arrayOf(
            intArrayOf(10, 60, 40),
            intArrayOf(50, 90, 20),
        )

        val actual = Problem1446().solution(d, routes)

        actual shouldBe 80
    }

    "case-3" {
        val d = 900
        val routes = arrayOf(
            intArrayOf(0, 10, 9),
            intArrayOf(20, 60, 45),
            intArrayOf(80, 190, 100),
            intArrayOf(50, 70, 15),
            intArrayOf(160, 180, 14),
            intArrayOf(140, 160, 14),
            intArrayOf(420, 901, 5),
            intArrayOf(450, 900, 0),
        )

        val actual = Problem1446().solution(d, routes)

        actual shouldBe 432
    }
})
