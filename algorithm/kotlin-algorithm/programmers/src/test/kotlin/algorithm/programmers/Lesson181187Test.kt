package algorithm.programmers

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class Lesson181187Test : StringSpec({
    "case-1" {
        val r1 = 2
        val r2 = 3

        val actual = Lesson181187().solution(r1, r2)

        actual shouldBe 20
    }
})
