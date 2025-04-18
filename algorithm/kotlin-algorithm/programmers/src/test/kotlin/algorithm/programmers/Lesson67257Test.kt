package algorithm.programmers

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class Lesson67257Test : StringSpec({
    "case-1" {
        val expression = "100-200*300-500+20"
        val actual = Lesson67257().solution(expression)
        actual shouldBe 60420
    }

    "case-2" {
        val expression = "50*6-3*2"
        val actual = Lesson67257().solution(expression)
        actual shouldBe 300
    }
})
