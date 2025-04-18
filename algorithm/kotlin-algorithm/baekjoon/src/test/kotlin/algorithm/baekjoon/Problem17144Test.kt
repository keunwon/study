package algorithm.baekjoon

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class Problem17144Test : StringSpec({
    "case-1" {
        val t = 1
        val board = arrayOf(
            intArrayOf(0, 0, 0, 0, 0, 0, 0, 9),
            intArrayOf(0, 0, 0, 0, 3, 0, 0, 8),
            intArrayOf(-1, 0, 5, 0, 0, 0, 22, 0),
            intArrayOf(-1, 8, 0, 0, 0, 0, 0, 0),
            intArrayOf(0, 0, 0, 0, 0, 10, 43, 0),
            intArrayOf(0, 0, 5, 0, 15, 0, 0, 0),
            intArrayOf(0, 0, 40, 0, 0, 0, 20, 0),
        )

        val actual = Problem17144().solution(t, board)

        actual shouldBe 188
    }

    "case-2" {
        val t = 2
        val board = arrayOf(
            intArrayOf(0, 0, 0, 0, 0, 0, 0, 9),
            intArrayOf(0, 0, 0, 0, 3, 0, 0, 8),
            intArrayOf(-1, 0, 5, 0, 0, 0, 22, 0),
            intArrayOf(-1, 8, 0, 0, 0, 0, 0, 0),
            intArrayOf(0, 0, 0, 0, 0, 10, 43, 0),
            intArrayOf(0, 0, 5, 0, 15, 0, 0, 0),
            intArrayOf(0, 0, 40, 0, 0, 0, 20, 0),
        )

        val actual = Problem17144().solution(t, board)

        actual shouldBe 188
    }

    "case-3" {
        val t = 3
        val board = arrayOf(
            intArrayOf(0, 0, 0, 0, 0, 0, 0, 9),
            intArrayOf(0, 0, 0, 0, 3, 0, 0, 8),
            intArrayOf(-1, 0, 5, 0, 0, 0, 22, 0),
            intArrayOf(-1, 8, 0, 0, 0, 0, 0, 0),
            intArrayOf(0, 0, 0, 0, 0, 10, 43, 0),
            intArrayOf(0, 0, 5, 0, 15, 0, 0, 0),
            intArrayOf(0, 0, 40, 0, 0, 0, 20, 0),
        )

        val actual = Problem17144().solution(t, board)

        actual shouldBe 186
    }

    "case-4" {
        val t = 4
        val board = arrayOf(
            intArrayOf(0, 0, 0, 0, 0, 0, 0, 9),
            intArrayOf(0, 0, 0, 0, 3, 0, 0, 8),
            intArrayOf(-1, 0, 5, 0, 0, 0, 22, 0),
            intArrayOf(-1, 8, 0, 0, 0, 0, 0, 0),
            intArrayOf(0, 0, 0, 0, 0, 10, 43, 0),
            intArrayOf(0, 0, 5, 0, 15, 0, 0, 0),
            intArrayOf(0, 0, 40, 0, 0, 0, 20, 0),
        )

        val actual = Problem17144().solution(t, board)

        actual shouldBe 178
    }

    "case-5" {
        val t = 5
        val board = arrayOf(
            intArrayOf(0, 0, 0, 0, 0, 0, 0, 9),
            intArrayOf(0, 0, 0, 0, 3, 0, 0, 8),
            intArrayOf(-1, 0, 5, 0, 0, 0, 22, 0),
            intArrayOf(-1, 8, 0, 0, 0, 0, 0, 0),
            intArrayOf(0, 0, 0, 0, 0, 10, 43, 0),
            intArrayOf(0, 0, 5, 0, 15, 0, 0, 0),
            intArrayOf(0, 0, 40, 0, 0, 0, 20, 0),
        )

        val actual = Problem17144().solution(t, board)

        actual shouldBe 172
    }

    "case-6" {
        val t = 20
        val board = arrayOf(
            intArrayOf(0, 0, 0, 0, 0, 0, 0, 9),
            intArrayOf(0, 0, 0, 0, 3, 0, 0, 8),
            intArrayOf(-1, 0, 5, 0, 0, 0, 22, 0),
            intArrayOf(-1, 8, 0, 0, 0, 0, 0, 0),
            intArrayOf(0, 0, 0, 0, 0, 10, 43, 0),
            intArrayOf(0, 0, 5, 0, 15, 0, 0, 0),
            intArrayOf(0, 0, 40, 0, 0, 0, 20, 0),
        )

        val actual = Problem17144().solution(t, board)

        actual shouldBe 71
    }

    "case-7" {
        val t = 30
        val board = arrayOf(
            intArrayOf(0, 0, 0, 0, 0, 0, 0, 9),
            intArrayOf(0, 0, 0, 0, 3, 0, 0, 8),
            intArrayOf(-1, 0, 5, 0, 0, 0, 22, 0),
            intArrayOf(-1, 8, 0, 0, 0, 0, 0, 0),
            intArrayOf(0, 0, 0, 0, 0, 10, 43, 0),
            intArrayOf(0, 0, 5, 0, 15, 0, 0, 0),
            intArrayOf(0, 0, 40, 0, 0, 0, 20, 0),
        )

        val actual = Problem17144().solution(t, board)

        actual shouldBe 52
    }

    "case-8" {
        val t = 50
        val board = arrayOf(
            intArrayOf(0, 0, 0, 0, 0, 0, 0, 9),
            intArrayOf(0, 0, 0, 0, 3, 0, 0, 8),
            intArrayOf(-1, 0, 5, 0, 0, 0, 22, 0),
            intArrayOf(-1, 8, 0, 0, 0, 0, 0, 0),
            intArrayOf(0, 0, 0, 0, 0, 10, 43, 0),
            intArrayOf(0, 0, 5, 0, 15, 0, 0, 0),
            intArrayOf(0, 0, 40, 0, 0, 0, 20, 0),
        )

        val actual = Problem17144().solution(t, board)

        actual shouldBe 46
    }
})
