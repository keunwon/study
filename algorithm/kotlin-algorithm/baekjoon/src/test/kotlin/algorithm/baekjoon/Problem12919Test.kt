package algorithm.baekjoon

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class Problem12919Test : StringSpec({
    "case-1" {
        val s = "A"
        val t = "BABA"

        val actual = Problem12919().solution(s, t)

        actual shouldBe 1
    }

    "case-2" {
        val s = "BAAAAABAA"
        val t = "BAABAAAAAB"

        val actual = Problem12919().solution(s, t)

        actual shouldBe 1
    }

    "case-3" {
        val s = "A"
        val t = "ABBA"

        val actual = Problem12919().solution(s, t)

        actual shouldBe 0
    }
})
