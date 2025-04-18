package algorithm.baekjoon

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class Problem4179Test : StringSpec({
    "case-1" {
        val board = arrayOf(
            "####".toCharArray(),
            "#JF#".toCharArray(),
            "#..#".toCharArray(),
            "#..#".toCharArray(),
        )

        val actual = Problem4179().solution(board)

        actual shouldBe "3"
    }
})
