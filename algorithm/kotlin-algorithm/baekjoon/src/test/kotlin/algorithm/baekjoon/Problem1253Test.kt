package algorithm.baekjoon

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class Problem1253Test : StringSpec({
    "case-1" {
        val numbers = intArrayOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
        val actual = Problem1253().solution(numbers)
        actual shouldBe 8
    }
})
