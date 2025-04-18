package algorithm.baekjoon

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class Problem17837Test : StringSpec({
    "case-1" {
        val map = arrayOf(
            intArrayOf(0, 0, 2, 0),
            intArrayOf(0, 0, 1, 0),
            intArrayOf(0, 0, 1, 2),
            intArrayOf(0, 2, 0, 0),
        )
        val infos = arrayOf(
            intArrayOf(2, 1, 1),
            intArrayOf(3, 2, 3),
            intArrayOf(2, 2, 1),
            intArrayOf(4, 1, 2),
        )

        val actual = Problem17837().solution(map, infos)

        actual shouldBe -1
    }

    "case-2" {
        val map = arrayOf(
            intArrayOf(0, 0, 0, 0),
            intArrayOf(0, 0, 0, 0),
            intArrayOf(0, 0, 0, 0),
            intArrayOf(0, 0, 0, 0),
        )
        val infos = arrayOf(
            intArrayOf(1, 1, 1),
            intArrayOf(1, 2, 1),
            intArrayOf(1, 3, 1),
            intArrayOf(1, 4, 1),
        )

        val actual = Problem17837().solution(map, infos)

        actual shouldBe 1
    }

    "case-3" {
        val map = arrayOf(
            intArrayOf(0, 0, 0, 0),
            intArrayOf(0, 0, 0, 0),
            intArrayOf(0, 0, 0, 0),
            intArrayOf(0, 0, 0, 0),
        )
        val infos = arrayOf(
            intArrayOf(1, 1, 1),
            intArrayOf(1, 2, 1),
            intArrayOf(1, 3, 1),
            intArrayOf(2, 4, 3),
        )

        val actual = Problem17837().solution(map, infos)

        actual shouldBe 1
    }

    "case-4" {
        val map = arrayOf(
            intArrayOf(0, 0, 0, 0),
            intArrayOf(0, 0, 0, 0),
            intArrayOf(0, 0, 0, 0),
            intArrayOf(0, 0, 0, 0),
        )
        val infos = arrayOf(
            intArrayOf(1, 1, 1),
            intArrayOf(1, 2, 1),
            intArrayOf(1, 3, 1),
            intArrayOf(3, 3, 3),
        )

        val actual = Problem17837().solution(map, infos)

        actual shouldBe 2
    }

    "case-5" {
        val map = arrayOf(
            intArrayOf(0, 1, 2, 0, 1, 1),
            intArrayOf(1, 2, 0, 1, 1, 0),
            intArrayOf(2, 1, 0, 1, 1, 0),
            intArrayOf(1, 0, 1, 1, 0, 2),
            intArrayOf(2, 0, 1, 2, 0, 1),
            intArrayOf(0, 2, 1, 0, 2, 1),
        )
        val infos = arrayOf(
            intArrayOf(1, 1, 1),
            intArrayOf(2, 2, 2),
            intArrayOf(3, 3, 4),
            intArrayOf(4, 4, 1),
            intArrayOf(5, 5, 3),
            intArrayOf(6, 6, 2),
            intArrayOf(1, 6, 3),
            intArrayOf(6, 1, 2),
            intArrayOf(2, 4, 3),
            intArrayOf(4, 2, 1),
        )

        val actual = Problem17837().solution(map, infos)

        actual shouldBe 7
    }
})
