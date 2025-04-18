package algorithm.programmers

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class Lesson43163Test : StringSpec({
    "case-1" {
        val begin = "hit"
        val target = "cog"
        val words = arrayOf("hot", "dot", "dog", "lot", "log", "cog")

        val actual = Lesson43163().solution(begin, target, words)

        actual shouldBe 4
    }

    "case-2" {
        val begin = "hit"
        val target = "cog"
        val words = arrayOf("hot", "dot", "dog", "lot", "log")

        val actual = Lesson43163().solution(begin, target, words)

        actual shouldBe 0
    }
})
