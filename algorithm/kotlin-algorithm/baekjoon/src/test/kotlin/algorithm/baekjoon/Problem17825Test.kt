package algorithm.baekjoon

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class Problem17825Test : StringSpec({
    "case-1" {
        val dices = intArrayOf(1, 2, 3, 4, 1, 2, 3, 4, 1, 2)
        val actual = Problem17825().solution(dices)
        actual shouldBe 190
    }

    "case-2" {
        val dices = intArrayOf(1, 1, 1, 1, 1, 1, 1, 1, 1, 1)
        val actual = Problem17825().solution(dices)
        actual shouldBe 133
    }

    "case-3" {
        val dices = intArrayOf(5, 1, 2, 3, 4, 5, 5, 3, 2, 4)
        val actual = Problem17825().solution(dices)
        actual shouldBe 214
    }

    "case-4" {
        val dices = intArrayOf(5, 5, 5, 5, 5, 5, 5, 5, 5, 5)
        val actual = Problem17825().solution(dices)
        actual shouldBe 130
    }

    "case-5" {
        val dices = intArrayOf(3, 1, 1, 5, 4, 5, 3, 3, 2, 5)
        val actual = Problem17825().solution(dices)
        actual shouldBe 204
    }

    "case-6" {
        val dices = intArrayOf(3, 5, 2, 5, 3, 5, 2, 1, 3, 1)
        val actual = Problem17825().solution(dices)
        actual shouldBe 246
    }

    "case-7" {
        val dices = intArrayOf(4, 1, 3, 4, 4, 4, 3, 3, 2, 4)
        val actual = Problem17825().solution(dices)
        actual shouldBe 211
    }

    "case-8" {
        val dices = intArrayOf(4, 1, 4, 4, 4, 4, 3, 2, 2, 4)
        val actual = Problem17825().solution(dices)
        actual shouldBe 205
    }

    "case-9" {
        val dices = intArrayOf(4, 3, 2, 1, 3, 4, 3, 4, 1, 2)
        val actual = Problem17825().solution(dices)
        actual shouldBe 202
    }


    "case-10" {
        val dices = intArrayOf(4, 5, 5, 5, 3, 4, 3, 5, 3, 4)
        val actual = Problem17825().solution(dices)
        actual shouldBe 229
    }

    "case-11" {
        val dices = intArrayOf(5, 3, 2, 5, 2, 4, 4, 2, 4, 1)
        val actual = Problem17825().solution(dices)
        actual shouldBe 231
    }

    "case-12" {
        val dices = intArrayOf(5, 3, 4, 3, 1, 3, 3, 3, 5, 2)
        val actual = Problem17825().solution(dices)
        actual shouldBe 216
    }

    "case-13" {
        val dices = intArrayOf(5, 4, 5, 2, 2, 2, 5, 3, 1, 4)
        val actual = Problem17825().solution(dices)
        actual shouldBe 245
    }

    "case-14" {
        val dices = intArrayOf(5, 5, 5, 5, 5, 1, 1, 1, 1, 1)
        val actual = Problem17825().solution(dices)
        actual shouldBe 167
    }

    "case-15" {
        val dices = intArrayOf(5, 5, 5, 5, 5, 2, 2, 1, 3, 3)
        val actual = Problem17825().solution(dices)
        actual shouldBe 161
    }

    "case-16" {
        val dices = intArrayOf(5, 5, 5, 5, 5, 2, 2, 2, 2, 2)
        val actual = Problem17825().solution(dices)
        actual shouldBe 160
    }
})
