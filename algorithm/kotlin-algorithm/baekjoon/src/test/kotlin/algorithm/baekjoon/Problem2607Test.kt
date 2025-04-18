package algorithm.baekjoon

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class Problem2607Test : StringSpec({
    "case-1" {
        val words = arrayOf(
            "DOG",
            "GOD",
            "GOOD",
            "DOLL",
            "ABC",
        )
        val actual = Problem2607().solution(words)
        actual shouldBe 2
    }

    "case-2" {
        val words = arrayOf("A", "B")
        val actual = Problem2607().solution(words)
        actual shouldBe 1
    }

    "case-3" {
        val words = arrayOf("AA", "AB")
        val actual = Problem2607().solution(words)
        actual shouldBe 1
    }
})
