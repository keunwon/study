package algorithm.baekjoon

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class Problem2164Test : StringSpec({
    "case-1" {
        val n = 6
        val actual = Problem2164().solution(n)
        actual shouldBe 4
    }
})
