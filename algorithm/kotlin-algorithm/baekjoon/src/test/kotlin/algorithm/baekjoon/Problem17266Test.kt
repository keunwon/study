package algorithm.baekjoon

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class Problem17266Test : StringSpec({
    "case-1" {
        val n = 5
        val positions = intArrayOf(2, 4)

        val actual = Problem17266().solution(n, positions)

        actual shouldBe 2
    }

    "case-2" {
        val n = 3
        val positions = intArrayOf(0)

        val actual = Problem17266().solution(n, positions)

        actual shouldBe 3
    }
})
