package algorithm.baekjoon

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class Problem14719Test : StringSpec({
    "case-1" {
        val h = 4
        val heights = intArrayOf(3, 0, 1, 4)

        val actual = Problem14719().solution(h, heights)

        actual shouldBe 5
    }

    "case-2" {
        val h = 4
        val heights = intArrayOf(3, 1, 2, 3, 4, 1, 1, 2)

        val actual = Problem14719().solution(h, heights)

        actual shouldBe 5
    }

    "case-3" {
        val h = 3
        val heights = intArrayOf(0, 0, 0, 2, 0)

        val actual = Problem14719().solution(h, heights)

        actual shouldBe 0
    }
})
