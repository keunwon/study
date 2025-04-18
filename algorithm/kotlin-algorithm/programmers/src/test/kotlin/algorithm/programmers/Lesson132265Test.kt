package algorithm.programmers

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class Lesson132265Test : StringSpec({
    "case-1" {
        val topping = intArrayOf(1, 2, 1, 3, 1, 4, 1, 2)
        val actual = Lesson132265().solution(topping)
        actual shouldBe 2
    }

    "case-2" {
        val topping = intArrayOf(1, 2, 3, 1, 4)
        val actual = Lesson132265().solution(topping)
        actual shouldBe 0
    }
})
