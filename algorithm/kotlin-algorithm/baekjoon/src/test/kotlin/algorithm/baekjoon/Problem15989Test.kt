package algorithm.baekjoon

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class Problem15989Test : StringSpec({
    "case-1" {
        val numbers = intArrayOf(4, 7, 10)
        val actual = Problem15989().solution(numbers)
        actual shouldBe intArrayOf(4, 8, 14)
    }
})
