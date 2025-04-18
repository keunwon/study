package algorithm.baekjoon

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class Problem17143Test : StringSpec({
    "case-1" {
        val r = 4
        val c = 6
        val sharkInfos = arrayOf(
            intArrayOf(4, 1, 3, 3, 8),
            intArrayOf(1, 3, 5, 2, 9),
            intArrayOf(2, 4, 8, 4, 1),
            intArrayOf(4, 5, 0, 1, 4),
            intArrayOf(3, 3, 1, 2, 7),
            intArrayOf(1, 5, 8, 4, 3),
            intArrayOf(3, 6, 2, 1, 2),
            intArrayOf(2, 2, 2, 3, 5),
        )

        val actual = Problem17143().solution(r, c, sharkInfos)

        actual shouldBe 22
    }

    "case-2" {
        val r = 100
        val c = 100
        val sharkInfos = arrayOf<IntArray>()

        val actual = Problem17143().solution(r, c, sharkInfos)

        actual shouldBe 0
    }

    "case-3" {
        val r = 4
        val c = 5
        val sharkInfos = arrayOf(
            intArrayOf(4, 1, 3, 3, 8),
            intArrayOf(1, 3, 5, 2, 9),
            intArrayOf(2, 4, 8, 4, 1),
            intArrayOf(4, 5, 0, 1, 4),
        )

        val actual = Problem17143().solution(r, c, sharkInfos)

        actual shouldBe 22
    }

    "case-4" {
        val r = 2
        val c = 2
        val sharkInfos = arrayOf(
            intArrayOf(1, 1, 1, 1, 1),
            intArrayOf(2, 2, 2, 2, 2),
            intArrayOf(1, 2, 1, 2, 3),
            intArrayOf(2, 1, 2, 1, 4),
        )

        val actual = Problem17143().solution(r, c, sharkInfos)

        actual shouldBe 4
    }

    "case-5" {
        val r = 2
        val c = 5
        val sharkInfos = arrayOf(intArrayOf(1, 5, 1, 3, 1))

        val actual = Problem17143().solution(r, c, sharkInfos)

        actual shouldBe 1
    }
})
