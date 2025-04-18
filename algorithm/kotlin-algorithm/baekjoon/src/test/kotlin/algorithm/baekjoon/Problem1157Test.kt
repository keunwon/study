package algorithm.baekjoon

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class Problem1157Test : StringSpec({
    "case-1" {
        val s = "Mississipi"
        val actual = Problem1157().solution(s)
        actual shouldBe "?"
    }

    "case-2" {
        val s = "zZa"
        val actual = Problem1157().solution(s)
        actual shouldBe "Z"
    }

    "case-3" {
        val s = "z"
        val actual = Problem1157().solution(s)
        actual shouldBe "Z"
    }

    "case-4" {
        val s = "baaa"
        val actual = Problem1157().solution(s)
        actual shouldBe "A"
    }
})
