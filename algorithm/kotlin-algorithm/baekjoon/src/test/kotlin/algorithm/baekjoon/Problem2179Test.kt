package algorithm.baekjoon

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class Problem2179Test : StringSpec({
    "case-1" {
        val words = arrayOf("noon", "is", "lunch", "for", "most", "noone", "waits", "until", "two")
        val actual = Problem2179().solution(words)
        actual shouldBe arrayOf("noon", "noone")
    }

    "case-2" {
        val words = arrayOf("abcd", "abe", "abc", "abchldp")
        val actual = Problem2179().solution(words)
        actual shouldBe arrayOf("abcd", "abc")
    }
})
