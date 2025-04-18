package algorithm.baekjoon

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class Problem22251Test : StringSpec({
    "case-1" {
        val n = 9
        val k = 1
        val p = 2
        val x = 5

        val actual = Problem22251().solution(n, k, p, x)

        actual shouldBe 4
    }

    "case-2" {
        val n = 48
        val k = 2
        val p = 5
        val x = 35

        val actual = Problem22251().solution(n, k, p, x)

        actual shouldBe 30
    }
})
