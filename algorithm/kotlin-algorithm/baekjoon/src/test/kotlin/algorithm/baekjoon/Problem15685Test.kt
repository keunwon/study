package algorithm.baekjoon

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class Problem15685Test : StringSpec({
    "case-1" {
        val infos = arrayOf(
            intArrayOf(3, 3, 0, 1),
            intArrayOf(4, 2, 1, 3),
            intArrayOf(4, 2, 2, 1),
        )
        val actual = Problem15685().solution(infos)
        actual shouldBe 4
    }

    "case-2" {
        val infos = arrayOf(
            intArrayOf(3, 3, 0, 1),
            intArrayOf(4, 2, 1, 3),
            intArrayOf(4, 2, 2, 1),
            intArrayOf(2, 7, 3, 4),
        )
        val actual = Problem15685().solution(infos)
        actual shouldBe 11
    }

    "case-3" {
        val infos = arrayOf(
            intArrayOf(5, 5, 0, 0),
            intArrayOf(5, 6, 0, 0),
            intArrayOf(5, 7, 0, 0),
            intArrayOf(5, 8, 0, 0),
            intArrayOf(5, 9, 0, 0),
            intArrayOf(6, 5, 0, 0),
            intArrayOf(6, 6, 0, 0),
            intArrayOf(6, 7, 0, 0),
            intArrayOf(6, 8, 0, 0),
            intArrayOf(6, 9, 0, 0),
        )
        val actual = Problem15685().solution(infos)
        actual shouldBe 8
    }

    "case-4" {
        val infos = arrayOf(
            intArrayOf(50, 50, 0, 10),
            intArrayOf(50, 50, 1, 10),
            intArrayOf(50, 50, 2, 10),
            intArrayOf(50, 50, 3, 10),
        )
        val actual = Problem15685().solution(infos)
        actual shouldBe 1992
    }
})
