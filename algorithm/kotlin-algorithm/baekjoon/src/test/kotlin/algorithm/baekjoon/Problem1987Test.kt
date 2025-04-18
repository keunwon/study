package algorithm.baekjoon

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class Problem1987Test : StringSpec({
    "case-1" {
        val board = arrayOf(
            "CAAB",
            "ADCB",
        )
        val actual = Problem1987().solution(board)
        actual shouldBe 3
    }

    "case-2" {
        val board = arrayOf(
            "HFDFFB",
            "AJHGDH",
            "DGAGEH",
        )
        val actual = Problem1987().solution(board)
        actual shouldBe 6
    }

    "case-3" {
        val board = arrayOf(
            "IEFCJ",
            "FHFKC",
            "FFALF",
            "HFGCF",
            "HMCHH",
        )
        val actual = Problem1987().solution(board)
        actual shouldBe 10
    }
})
