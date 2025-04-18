package algorithm.baekjoon

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class Problem1522Test : StringSpec({
    "case-1" {
        val str = "abababababababa"
        val actual = Problem1522().solution(str)
        actual shouldBe 3
    }

    "case-2" {
        val str = "ba"
        val actual = Problem1522().solution(str)
        actual shouldBe 0
    }

    "case-3" {
        val str = "aaaabbbbba"
        val actual = Problem1522().solution(str)
        actual shouldBe 0
    }

    "case-4" {
        val str = "abab"
        val actual = Problem1522().solution(str)
        actual shouldBe 1
    }

    "case-5" {
        val str = "aabbaaabaaba"
        val actual = Problem1522().solution(str)
        actual shouldBe 2
    }

    "case-6" {
        val str = "aaaa"
        val actual = Problem1522().solution(str)
        actual shouldBe 0
    }
})
