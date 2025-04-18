package algorithm.programmers

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class Lesson12983Test : StringSpec({
    "case-1" {
        val strs = arrayOf("ba", "na", "n", "a")
        val t = "banana"

        val actual = Lesson12983().solution(strs, t)

        actual shouldBe 3
    }

    "case-2" {
        val strs = arrayOf("app", "ap", "p", "l", "e", "ple", "pp")
        val t = "apple"

        val actual = Lesson12983().solution(strs, t)

        actual shouldBe 2
    }

    "case-3" {
        val strs = arrayOf("ba", "an", "nan", "ban", "n")
        val t = "banana"

        val actual = Lesson12983().solution(strs, t)

        actual shouldBe -1
    }
})
