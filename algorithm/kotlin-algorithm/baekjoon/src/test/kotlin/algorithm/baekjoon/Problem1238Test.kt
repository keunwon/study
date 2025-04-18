package algorithm.baekjoon

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class Problem1238Test : StringSpec({
    "case-1" {
        val n = 4
        val x = 2
        val edges = arrayOf(
            intArrayOf(1, 2, 4),
            intArrayOf(1, 3, 2),
            intArrayOf(1, 4, 7),
            intArrayOf(2, 1, 1),
            intArrayOf(2, 3, 5),
            intArrayOf(3, 1, 2),
            intArrayOf(3, 4, 4),
            intArrayOf(4, 2, 3),
        )

        val actual = Problem1238().solution(n, x, edges)

        actual shouldBe 10
    }
})
