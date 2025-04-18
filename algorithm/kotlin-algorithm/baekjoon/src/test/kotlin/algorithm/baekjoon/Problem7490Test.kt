package algorithm.baekjoon

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class Problem7490Test : StringSpec({
    "case-1" {
        val n = 3
        val actual = Problem7490().solution(n)
        actual shouldBe arrayOf("1+2-3")
    }

    "case-2" {
        val n = 7
        val actual = Problem7490().solution(n)
        actual shouldBe arrayOf(
            "1+2-3+4-5-6+7",
            "1+2-3-4+5+6-7",
            "1-2 3+4+5+6+7",
            "1-2 3-4 5+6 7",
            "1-2+3+4-5+6-7",
            "1-2-3-4-5+6+7",
        )
    }
})
