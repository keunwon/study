package algorithm.programmers

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class Lesson181872Test : StringSpec({
    "case-1" {
        val myString = "AbCdEFG"
        val pat = "dE"

        val actual = Lesson181872().solution(myString, pat)

        actual shouldBe "AbCdE"
    }

    "case-2" {
        val myString = "AAAAaaaa"
        val pat = "a"

        val actual = Lesson181872().solution(myString, pat)

        actual shouldBe "AAAAaaaa"
    }
})
