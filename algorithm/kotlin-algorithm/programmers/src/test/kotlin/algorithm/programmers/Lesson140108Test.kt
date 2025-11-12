package algorithm.programmers

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class Lesson140108Test : StringSpec({
    "case-1" {
        val s = "banana"
        val actual = Lesson140108().solution(s)
        actual shouldBe 3
    }

    "case-2" {
        val s = "abracadabra"
        val actual = Lesson140108().solution(s)
        actual shouldBe 6
    }

    "case-3" {
        val s = "aaabbaccccabba"
        val actual = Lesson140108().solution(s)
        actual shouldBe 3
    }
})
