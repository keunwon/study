package algorithm.baekjoon

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class Problem20056Test : StringSpec({
    "case-1" {
        val n = 4
        val k = 1
        val infos = arrayOf(
            intArrayOf(1, 1, 5, 2, 2),
            intArrayOf(1, 4, 7, 1, 6),
        )

        val actual = Problem20056().solution(n, k, infos)

        actual shouldBe 8
    }

    "case-2" {
        val n = 4
        val k = 2
        val infos = arrayOf(
            intArrayOf(1, 1, 5, 2, 2),
            intArrayOf(1, 4, 7, 1, 6),
        )

        val actual = Problem20056().solution(n, k, infos)

        actual shouldBe 8
    }

    "case-3" {
        val n = 4
        val k = 3
        val infos = arrayOf(
            intArrayOf(1, 1, 5, 2, 2),
            intArrayOf(1, 4, 7, 1, 6),
        )

        val actual = Problem20056().solution(n, k, infos)

        actual shouldBe 0
    }

    "case-4" {
        val n = 7
        val k = 3
        val infos = arrayOf(
            intArrayOf(1, 3, 5, 2, 4),
            intArrayOf(2, 3, 5, 2, 6),
            intArrayOf(5, 2, 9, 1, 7),
            intArrayOf(6, 2, 1, 3, 5),
            intArrayOf(4, 4, 2, 4, 2),
        )

        val actual = Problem20056().solution(n, k, infos)

        actual shouldBe 9
    }
})
