package algorithm.baekjoon

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class Problem21921Test : StringSpec({
    "case-1" {
        val x = 2
        val days = intArrayOf(1, 4, 2, 5, 1)

        val actual = Problem21921().solution(x, days)

        actual shouldBe "7\n1"
    }

    "case-2" {
        val x = 5
        val days = intArrayOf(1, 1, 1, 1, 1, 5, 1)

        val actual = Problem21921().solution(x, days)

        actual shouldBe "9\n2"
    }

    "case-3" {
        val x = 3
        val days = intArrayOf(0, 0, 0, 0, 0)

        val actual = Problem21921().solution(x, days)

        actual shouldBe "SAD"
    }
})
