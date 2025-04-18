package algorithm.baekjoon

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class Problem13305Test : StringSpec({
    "case-1" {
        val distances = intArrayOf(2, 3, 1)
        val prices = intArrayOf(5, 2, 4, 1)

        val actual = Problem13305().solution(distances, prices)

        actual shouldBe 18L
    }

    "case-2" {
        val distances = intArrayOf(3, 3, 4)
        val prices = intArrayOf(1, 1, 1, 1)

        val actual = Problem13305().solution(distances, prices)

        actual shouldBe 10L
    }
})
