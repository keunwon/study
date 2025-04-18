package algorithm.baekjoon

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class Problem19637Test : StringSpec({
    "case-1" {
        val conditions = arrayOf(
            "WEAK" to 10000,
            "NORMAL" to 100000,
            "STRONG" to 1000000,
        )
        val powers = intArrayOf(0, 9999, 10000, 10001, 50000, 100000, 500000, 1000000)

        val actual = Problem19637().solution(conditions, powers)

        actual shouldBe arrayOf(
            "WEAK",
            "WEAK",
            "WEAK",
            "NORMAL",
            "NORMAL",
            "NORMAL",
            "STRONG",
            "STRONG",
        )
    }

    "case-2" {
        val conditions = arrayOf(
            "B" to 100,
            "A" to 100,
            "C" to 1000,
        )
        val powers = intArrayOf(99, 100, 101, 500, 1000)

        val actual = Problem19637().solution(conditions, powers)

        actual shouldBe arrayOf("B", "B", "C", "C", "C")
    }
})
