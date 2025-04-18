package algorithm.baekjoon

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class Problem20437Test : StringSpec({
    "case-1" {
        val s = "superaquatornado"
        val k = 2

        val actual = Problem20437().solution(s, k)

        actual shouldBe intArrayOf(4, 8)
    }

    "case-2" {
        val s = "abcdefghijklmnopqrstuvwxyz"
        val k = 5

        val actual = Problem20437().solution(s, k)

        actual shouldBe intArrayOf(-1)
    }

    "case-3" {
        val s = "abaaaba"
        val k = 3

        val actual = Problem20437().solution(s, k)

        actual shouldBe intArrayOf(3, 4)
    }

    "case-4" {
        val s = "superaquatornado"
        val k = 1

        val actual = Problem20437().solution(s, k)

        actual shouldBe intArrayOf(1, 1)
    }
})
