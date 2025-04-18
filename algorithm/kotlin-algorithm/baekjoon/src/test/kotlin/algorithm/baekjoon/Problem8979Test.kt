package algorithm.baekjoon

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class Problem8979Test : StringSpec({
    "case-1" {
        val k = 3
        val infos = arrayOf(
            intArrayOf(1, 1, 2, 0),
            intArrayOf(2, 0, 1, 0),
            intArrayOf(3, 0, 1, 0),
            intArrayOf(4, 0, 0, 1),
        )

        val actual = Problem8979().solution(k, infos)

        actual shouldBe 2
    }

    "case-2" {
        val k = 2
        val infos = arrayOf(
            intArrayOf(1, 3, 0, 0),
            intArrayOf(3, 0, 0, 2),
            intArrayOf(4, 0, 2, 0),
            intArrayOf(2, 0, 2, 0),
        )

        val actual = Problem8979().solution(k, infos)

        actual shouldBe 2
    }
})
