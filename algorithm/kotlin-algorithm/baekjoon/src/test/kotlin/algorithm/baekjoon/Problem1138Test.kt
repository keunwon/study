package algorithm.baekjoon

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class Problem1138Test : StringSpec({
    "case-1" {
        val numbers = intArrayOf(2, 1, 1, 0)
        val actual = Problem1138().solution(numbers)
        actual shouldBe intArrayOf(4, 2, 1, 3)
    }

    "case-2" {
        val numbers = intArrayOf(0, 0, 0, 0, 0)
        val actual = Problem1138().solution(numbers)
        actual shouldBe intArrayOf(1, 2, 3, 4, 5)
    }

    "case-3" {
        val numbers = intArrayOf(5, 4, 3, 2, 1, 0)
        val actual = Problem1138().solution(numbers)
        actual shouldBe intArrayOf(6, 5, 4, 3, 2, 1)
    }

    "case-4" {
        val numbers = intArrayOf(6, 1, 1, 1, 2, 0, 0)
        val actual = Problem1138().solution(numbers)
        actual shouldBe intArrayOf(6, 2, 3, 4, 7, 5, 1)
    }
})
