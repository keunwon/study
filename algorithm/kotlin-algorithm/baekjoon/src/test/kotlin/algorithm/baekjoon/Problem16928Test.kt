package algorithm.baekjoon

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class Problem16928Test : StringSpec({
    "case-1" {
        val infos = arrayOf(
            intArrayOf(32, 62),
            intArrayOf(42, 68),
            intArrayOf(12, 98),
            intArrayOf(95, 13),
            intArrayOf(97, 25),
            intArrayOf(93, 37),
            intArrayOf(79, 27),
            intArrayOf(75, 19),
            intArrayOf(49, 47),
            intArrayOf(67, 17),
        )

        val actual = Problem16928().solution(infos)

        actual shouldBe 3
    }

    "case-2" {
        val infos = arrayOf(
            intArrayOf(8, 52),
            intArrayOf(6, 80),
            intArrayOf(26, 42),
            intArrayOf(2, 72),
            intArrayOf(51, 19),
            intArrayOf(39, 11),
            intArrayOf(37, 29),
            intArrayOf(81, 3),
            intArrayOf(59, 5),
            intArrayOf(79, 23),
            intArrayOf(53, 7),
            intArrayOf(43, 33),
            intArrayOf(77, 21),
        )

        val actual = Problem16928().solution(infos)

        actual shouldBe 5
    }
})
