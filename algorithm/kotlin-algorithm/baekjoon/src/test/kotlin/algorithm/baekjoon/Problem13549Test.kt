package algorithm.baekjoon

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class Problem13549Test : StringSpec({
    "case-1" {
        val n = 5
        val k = 17

        val actual = Problem13549().solution(n, k)

        actual shouldBe 2
    }
})
