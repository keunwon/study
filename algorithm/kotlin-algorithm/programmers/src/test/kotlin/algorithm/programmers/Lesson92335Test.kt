package algorithm.programmers

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class Lesson92335Test : StringSpec({
    "case-1" {
        val n = 437674
        val k = 3

        val actual = Lesson92335().solution(n, k)

        actual shouldBe 3
    }

    "case-2" {
        val n = 110011
        val k = 10

        val actual = Lesson92335().solution(n, k)

        actual shouldBe 2
    }
})
