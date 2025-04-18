package algorithm.baekjoon

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class Problem2206KtTest : StringSpec({
    "case-1" {
        val board = arrayOf(
            charArrayOf('0', '1', '0', '0'),
            charArrayOf('1', '1', '1', '0'),
            charArrayOf('1', '0', '0', '0'),
            charArrayOf('0', '0', '0', '0'),
            charArrayOf('0', '1', '1', '1'),
            charArrayOf('0', '0', '0', '0'),
        )
        val actual = Problem2206().solution(board)
        actual shouldBe 15
    }

    "case-2" {
        val board = arrayOf(
            charArrayOf('0', '1', '1', '1'),
            charArrayOf('1', '1', '1', '1'),
            charArrayOf('1', '1', '1', '1'),
            charArrayOf('1', '1', '1', '0'),
        )
        val actual = Problem2206().solution(board)
        actual shouldBe -1
    }
})
