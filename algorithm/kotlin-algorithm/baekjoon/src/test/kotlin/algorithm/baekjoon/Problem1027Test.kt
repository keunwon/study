package algorithm.baekjoon

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class Problem1027Test : StringSpec({
    "case-1" {
        val heights = intArrayOf(3, 7, 1, 6, 3, 5, 1, 7)
        val actual = Problem1027().solution(heights)
        actual shouldBe arrayOf(
            intArrayOf(1, 2),
            intArrayOf(0),
            intArrayOf(3, 2),
            intArrayOf(2, 2),
            intArrayOf(4, 4),
            intArrayOf(3, 4),
            intArrayOf(4, 6),
            intArrayOf(0),
        )
    }
})
