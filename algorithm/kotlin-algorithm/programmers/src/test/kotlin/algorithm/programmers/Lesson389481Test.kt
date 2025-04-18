package algorithm.programmers

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class Lesson389481Test : StringSpec({
    "case-1" {
        val n = 30L
        val bans = arrayOf("d", "e", "bb", "aa", "ae")

        val actual = Lesson389481().solution(n, bans)

        actual shouldBe "ah"
    }

    "case-2" {
        val n = 7388L
        val bans = arrayOf("gqk", "kdn", "jxj", "jxi", "fug", "jxg", "ewq", "len", "bhc")

        val actual = Lesson389481().solution(n, bans)

        actual shouldBe "jxk"
    }

    "case-3" {
        val n = 52L
        val bans = emptyArray<String>()

        val actual = Lesson389481().solution(n, bans)

        actual shouldBe "az"
    }

    "case-4" {
        val n = 217180147157L
        val bans = arrayOf("abcd", "zefdgd")

        val actual = Lesson389481().solution(n, bans)

        actual shouldBe "aaaaaaaaa"
    }
})
